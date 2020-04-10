package com.biz.score.service;

import org.springframework.stereotype.Service;

import com.biz.score.dao.StudentDao;
import com.biz.score.domain.ScoreVO;
import com.biz.score.domain.StudentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

	private final StudentDao studentDao;
	
	public int insert(StudentVO studentVO) {
		// TODO Auto-generated method stub
		
		
		// 학번 가져오기
		int maxSt_num = studentDao.getMaxStNum();
		
		String st_num = String.format("%04d", maxSt_num+1);
		
		log.debug("추가할 값:" + st_num);
		
		
		studentVO.setSt_num(st_num);
		
		
		
		
		
		
		return studentDao.insert(studentVO);
	}

	public String findStNameBySNum(String s_num) {
		// TODO Auto-generated method stub
		
		String st_name = studentDao.findStNameBySNum(s_num);
		
		
		if(st_name == null) {
			return st_name ="";
		}
		
		
		
		return  st_name;
	}

	public int insertScore(ScoreVO scoreVO) {
		// TODO Auto-generated method stub
		
		
		return studentDao.insertScore(scoreVO);
	}


	
	
}
