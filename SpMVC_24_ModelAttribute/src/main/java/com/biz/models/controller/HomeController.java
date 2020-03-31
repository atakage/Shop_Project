package com.biz.models.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends UserController{		// modelAttirbute 사용하려면 무조건 상속?
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		
		
		return "home";
	}
	
}
