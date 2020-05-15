package com.biz.tour.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.tour.domain.marinelife.MarineLifeAPIVO;
import com.biz.tour.service.marinelife.MarineLifeAPIService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/marinelifeapi")
@RequiredArgsConstructor
@Slf4j
public class MarineLifeAPIController {
	
private final MarineLifeAPIService animalService;

	



//낚시Base의 latD(latitude degree, 위도의 degree), lonD(longitude degree, 경도의 degree) 값과 매칭되는 해양 생물 데이터 SELECT
	@RequestMapping(value="/getXYmarine", method=RequestMethod.GET)
	public String getXYMarine() {
		
		 int mapX=36;
		 int mapY=128;
		 
		 animalService.getXYMarine(mapX, mapY);
		 
		 return null;
	}






	
	/*
	@RequestMapping(value="/animal",method=RequestMethod.GET)
	public String getAnimals() throws ParseException {
		
		
		// 해양생물 map에 일정 정보를 모으지 못했을 경우 api 재호출 위해 필요
		int pageNo = 1;
		
		// 해양생물 map에 일정 정보를 모으지 못했을 경우 api 재호출 위해 필요
		Map<String ,String> selectedAnimalMap = new HashMap<String,String>();
		animalService.getAnimals(pageNo, selectedAnimalMap);
		
		return null;
	}
	*/






/*
	// 해양 생물 테이블 전체 SELECT
	@RequestMapping(value="/getallmarine", method=RequestMethod.GET)
	public String getAllMarine() {
		
		 animalService.getAllMarine();
		 
		 return null;
	}
*/

/*	
	// 해양생물 API의 데이터를 DB에 저장하는 작업
	@RequestMapping(value="/animaltable",method=RequestMethod.GET)
	public String animalTable(){
		
		
		
		int ret = animalService.insertService();
		
		return null;
	}
*/
	
	}
