package com.biz.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/nation")
@Controller
public class NationController {

	
	
	@ResponseBody
	@RequestMapping("/where")
	public String where() {
	
		String nation = "대한민국\n";
		nation += "Korea";
		nation += "우리나라\n";
		
		return nation;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/korea")
	public String korea() {
	
		String nation = "나의조국ㄷ한민국";
		
		
		return nation;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/usa")
	public String usa() {
	
		String nation = "United State America";
		
		
		return nation;
	}
}
