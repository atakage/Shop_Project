package com.biz.testc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.testc.service.testService;

import lombok.RequiredArgsConstructor;

/**
 * Handles requests for the application home page.
 */

@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final testService tService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		
		
		tService.scheduledString();
	
		
		return "home";
	}
	
}
