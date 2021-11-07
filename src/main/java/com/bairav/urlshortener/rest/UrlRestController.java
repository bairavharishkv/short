package com.bairav.urlshortener.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.bairav.urlshortener.converter.Converter;
import com.bairav.urlshortener.entity.Statistics;
import com.bairav.urlshortener.entity.Url;
import com.bairav.urlshortener.service.StatisticsService;
import com.bairav.urlshortener.service.UrlService;

@RestController
public class UrlRestController {
	
	@Autowired
	private UrlService urlService;
	
	@Autowired
	private StatisticsService statisticsService;


	//function takes in a long URL from a POST request and returns a short one
	
	@PostMapping("/shortener")
	public Url addUrl(@RequestBody Url theUrl) {

		//initialize URL object
	
		theUrl.setId(0L);
		theUrl.setShortUrlkey("");
		theUrl.setCreate("");
		
		//if the long URL is invalid an error message is returned
		
		UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		boolean isValidUrl = urlValidator.isValid(theUrl.getLongUrl());
		
		if (theUrl.getLongUrl() == "" || theUrl.getLongUrl() == null || !isValidUrl) {
			
			theUrl.setLongUrl("not a valid value");
			
			return theUrl;
		}
		
		//if the long URL is already in the database the corresponding short URL is returned
		
		long inTheDatabase = urlService.isLongUrlInDB(theUrl.getLongUrl());
		
		if(inTheDatabase != -1L) {
			
			theUrl = urlService.getUrl(inTheDatabase);
			
			return theUrl;	
		}
		
		//store the time of creation in the database
		
		String timeCreated = LocalDateTime.now().toString();
		theUrl.setCreate(timeCreated);
		
		urlService.saveUrl(theUrl);   //save the Url object in the database
		
		/*
		 * Once the Url object is saved in the database its primary key will serve as the 
		 * unique number from which a shorter (base 62) key is generated. The short URL is
		 * then: http://localhost:8080/short/unique-key
		 */
		
		long id = theUrl.getId();  //database primary key
		
		theUrl.setShortUrlkey(Converter.createUniqueId(id)); //convert primary key to a base 62 number and save it to the object
		
		urlService.saveUrl(theUrl); //save the URL object in the database
	
		return theUrl;	
	}
	
	
	/*
	 * When the short URL link is clicked on a GET request is sent and
	 * the following function redirects the browser to the long URL.
	 */
	
	@GetMapping("/{uniqueKey}")  
	public RedirectView redirectToLongUrl(@PathVariable String uniqueKey,
									HttpServletRequest request) {
		
		//when the short URL is clicked on the IP address of the visitor is saved to the database
		String ipAddress = request.getRemoteAddr();
		
		RedirectView redirectView = new RedirectView();
		
		//validate the short URL
		
		long id = urlService.isValidShortUrl(uniqueKey); //if valid returns the primary key of the short URL entry
		
		/*
		 * If short URL is invalid a GET request is sent to @GetMapping("/invalidKey/{uniqueKey}")
		 * where the browser is redirected to a web page with an error message. 
		 */
		
		if (id == -1L) {  
			
			String redirectError = "invalidKey/" + uniqueKey;
			
			redirectView.setUrl(redirectError);
			
			return redirectView;		
		}
		
		Url theUrl = urlService.getUrl(id);
		
		//Save the visitor's IP address and time visited to the statistics table in the database
		
		Statistics theStatistics = new Statistics();

		//initialize Statistics object 
		theStatistics.setId(0L);

		//Store the current time 
		String timeVisited = LocalDateTime.now().toString();
		theStatistics.setTime(timeVisited);
		
		theStatistics.setIpAddress(ipAddress);
		
		theStatistics.setUrl(theUrl);  //the primary key of the associated short URL is the foreign key
									   //in the statistics table
		
		statisticsService.saveStatistics(theStatistics); //save Statistic object to database
		
		redirectView.setUrl(theUrl.getLongUrl()); //redirect the browser to the long URL
		
		return redirectView;
	}
	
	
	//if an incorrect short URL is used an error message is returned
	
	@GetMapping("/invalidKey/{uniqueKey}")
	public String handleError(@PathVariable String uniqueKey) {
		
		return uniqueKey + " is not a valid short key";
	}
	
	
	//return visitor statistics such as time visited and IP address
	
	@GetMapping("/info/{uniqueKey}") 
	public List<Statistics> getLinkStatistics(@PathVariable String uniqueKey) {
		
		long id = urlService.isValidShortUrl(uniqueKey); //if valid returns the primary key of the short URL entry
		
		if (id == -1L) {  //if an invalid short URL an error message is returned
			
			List<Statistics> errorList = new ArrayList<>();
			
			Statistics theError = new Statistics();
			
			//initialize the object with error messages
			theError.setId(0L);
			theError.setTime("invalid short URL");
			theError.setIpAddress("invalid short URL");

			return errorList;
		}	
		
		List<Statistics> theStatistics = urlService.getStatistics(id); //retrieve the statistics
		
		return theStatistics;
	}
}
