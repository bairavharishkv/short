package com.bairav.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bairav.urlshortener.entity.Url;

@Controller
public class HomeController {
	
	@GetMapping("/welcome")
	public String showWelcomePage(Model theModel) {
		
		Url theUrl = new Url();
		theModel.addAttribute("url", theUrl);
		theModel.addAttribute("theShortenedUrl", "");
		return "home";
	}

}
