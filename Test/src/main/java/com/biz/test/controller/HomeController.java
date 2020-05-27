package com.biz.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.test.domain.TestVO;
import com.biz.test.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	
	
	private final TestService tService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		
		tService.getQuery();
		
		
		
		return "home";
	}
	
}
