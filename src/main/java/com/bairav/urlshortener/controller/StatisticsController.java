package com.bairav.urlshortener.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bairav.urlshortener.entity.Statistics;
import com.bairav.urlshortener.entity.Url;
import com.bairav.urlshortener.service.UrlService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	
	@Autowired
	private UrlService urlService;

	//returns the statistics home page
	
	@GetMapping("/home")
	public String statisticsHomePage(Model theModel) {
		
		//create dummy placeholder Url object to add to the model
		Url placeholderUrl = new Url();

		placeholderUrl.setId(0L);
		theModel.addAttribute("url", placeholderUrl);
			
		return "statistics";
	}
	
	//takes as input a short URL and returns a table with visitor statistics
	
	@PostMapping("/list")
	public String listStatistics(@Valid @ModelAttribute("url") Url theUrl, Model theModel) {	
		
		//users can enter all or part of the short URL, only the unique-id at the end is needed
		
		String shortUrl = theUrl.getShortUrlkey();
		
		String[] shortUrlSplit = theUrl.getShortUrlkey().split("/");
		
		String theUniqueKey = shortUrlSplit[shortUrlSplit.length - 1];  
	
		//validate the short URL
		
		long id=-2L;
		
		if(theUniqueKey != "" && theUniqueKey != null)
			id = urlService.isValidShortUrl(theUniqueKey);  //returns the id of the short URL if valid
		
		if ( id < 0L) { //if the the short URL is invalid redirect to a web page with an error message
			
			theUrl.setShortUrlkey("");
			
			theModel.addAttribute("url", theUrl);
			
			return "redirect:/statistics/noShortUrlError";
		}
		
		 //add the statistics table to the model to be rendered by the view
		
		List<Statistics> theStatistics = urlService.getStatistics(id); 
		theModel.addAttribute("theStatistics", theStatistics);
		
		//add a table title to the model to be rendered by the view
		
		String tableTitle = "Visitors for " + shortUrl;
		theModel.addAttribute("tableTitle", tableTitle);
		
		return "table";
	}
	
	//returns an error message if the short URL is invalid
	
	@GetMapping("/noShortUrlError")
	public String noShortUrlError() {
		
		return  "noShortUrlError";
	}

}
