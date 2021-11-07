package com.bairav.urlshortener.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bairav.urlshortener.converter.Converter;
import com.bairav.urlshortener.entity.Url;
import com.bairav.urlshortener.service.UrlService;

@Controller
public class UrlController {
	
	@Autowired
	private UrlService urlService;	
	
	
	//function takes in a long URL from a POST request and returns a short one
	
	@PostMapping("/postLongUrl")
	public String postLongUrl(@Valid @ModelAttribute("url") Url theUrl, Model theModel) {
		
		//If the long URL is invalid the user is returned to the welcome page
		
		UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		boolean isValidUrl = urlValidator.isValid(theUrl.getLongUrl());
		
		if (theUrl.getLongUrl() == "" || theUrl.getLongUrl() == null || !isValidUrl)  {
			
			theUrl.setLongUrl("");
			theModel.addAttribute("url", theUrl);
			theModel.addAttribute("theShortenedUrl", "");
			
			return "redirect:/welcome";
		}
		
		//if the long URL is already in the database the corresponding short URL is returned
		
		long inTheDatabase = urlService.isLongUrlInDB(theUrl.getLongUrl());
		
		if(inTheDatabase != -1L) {  
			
			theUrl = urlService.getUrl(inTheDatabase);
			
			String shortUrl = "Your short URL is: http://localhost:8080/short/" + theUrl.getShortUrlkey();
			
			theModel.addAttribute("theShortenedUrl", shortUrl);
			
			 return "home";
		}

		//initialize URL object
		
		theUrl.setId(0L);
		theUrl.setShortUrlkey("");
		
		//store the time of creation in the database
		
		String timeCreated = LocalDateTime.now().toString();
		theUrl.setCreate(timeCreated);
		
		urlService.saveUrl(theUrl);    //save the Url object in the database
		
		/*
		 * Once the Url object is saved in the database its primary key will serve as the 
		 * unique number from which a shorter (base 62) key is generated. The short URL is
		 * then: http://localhost:8080/short/unique-key
		 */
		
		long id = theUrl.getId(); //database primary key
		
		theUrl.setShortUrlkey(Converter.createUniqueId(id)); //convert primary key to a base 62 number and save it to the object
		
		urlService.saveUrl(theUrl); //save the URL object in the database
		
		//publish the short URL on the home web page
		
		String shortUrl = "Your short Url is: http://localhost:8080/short/" + theUrl.getShortUrlkey();
		
		theModel.addAttribute("theShortenedUrl", shortUrl);
		
		 return "home";	
	}
	
	//returns instructions that describe the web service
	
	@GetMapping("/webservice")
	public String webServiceHome() {
		return "webservice";
	}

}
