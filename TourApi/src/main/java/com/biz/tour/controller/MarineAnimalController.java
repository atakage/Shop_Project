package com.biz.tour.controller;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.tour.service.MarineAnimalService;
import com.google.gson.JsonArray;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MarineAnimalController {
	
	private final MarineAnimalService animalService;

	
	
	
	@RequestMapping(value="/animal",method=RequestMethod.GET)
	public String getAnimals() throws ParseException {
		JsonArray animaljSonArr = animalService.getAnimals();
		
		return null;
	}
	
}
