package com.biz.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.test.dao.TestDao;
import com.biz.test.domain.TestVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {

	
	private final TestDao testDao;
	
	public void getQuery() {
		List<TestVO> testList = testDao.getQuery();
		
		
		log.debug("TESTLIST: " + testList.toString());
		
	}

	
	
	
}
