package com.biz.testc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class testService {

	public void callString() {
		// TODO Auto-generated method stub
		
		log.debug("콜스트링");
		
	}
	
	
	@Scheduled(fixedDelay = 5000)
	public void scheduledString() {
		log.debug("스케줄스트링");
	}

	
}
