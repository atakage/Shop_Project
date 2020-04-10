package com.biz.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.score.domain.ScoreVO;
import com.biz.score.domain.StudentVO;
import com.biz.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/student")
@Controller
public class StudentController {

	
	private final StudentService studentService;
	
	
	@ResponseBody
	@RequestMapping(value="/insert", method=RequestMethod.POST, produces = "application/text;charset=utf8")
	public String insert(StudentVO studentVO) {
		
	
		log.debug("학생정보"+studentVO.toString());
		
		// 학번 서비스에서 넣기
		int ret = studentService.insert(studentVO);
		
		
		
		if(ret > 0) {
			
			return "등록 완료";
		}
		
		return "등록 실패";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/findstname", method=RequestMethod.POST)
	public String findStName(String s_num) {
		
		
		String s_name = studentService.findStNameBySNum(s_num);
		
		if(s_name == "") {
			return "null";
		}
		
		return s_name;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/insertscore", method=RequestMethod.POST)
	public String insertScore(ScoreVO scoreVO) {
		
		
		log.debug("넘어온스코어:" + scoreVO.toString());
		
		scoreVO.setS_total(scoreVO.getS_english()+scoreVO.getS_korean()+scoreVO.getS_math());
		scoreVO.setS_average(scoreVO.getS_total()/3);
		
		int ret = studentService.insertScore(scoreVO);
		
		if(ret > 0) {
			
			return "ok";
		}
		
		
		
		
		return "fail";
	}
	
	
	
}
