package com.biz.bbs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * class에 부착하는 requestmapping
 * type 수준의 Req
 * top level Req
 * 
 * 메서드에 /list라고 RequestMapping을 붙이면 호출을 할 때 context/comment/list 라고 path를 지정
 */


@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/comment")
@Controller
public class CommentController {
	
	
	private final CommentService commentService;
	
	
	/*
	 * 
	 * 게시판의 id값을 매개변수로 받아서
	 * 코멘트 리스트를 보여주는 method
	 * 
	 */
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(String b_id, Model model){
		
		
		long c_b_id = Long.valueOf(b_id);
		List<CommentVO> cmtList = commentService.findByBId(c_b_id);
		
		model.addAttribute("COMMENT", cmtList);
		
		return "comment_list";
		
	}
	
	/*
	 * 코멘트 입력값을 매개변수로 받아서 db insert를 수행할 method
	 */
	
	//@ResponseBody
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(CommentVO commentVO, Model model) {
		
		
		log.debug("브이오" + commentVO);
		
		
		
		int ret = commentService.insert(commentVO);
		
		
		// 일반적인 방법
		// long c_b_id = cmtVO.getC_b_id();
		// return "redirect:/detail?b_id=" + c_b_id
		
		
		// 모델에 값 넣고 redirect해도 같이 따라감
		// 쿼리문자열을 자동으로 만들어 전달함
		
		
		
		
		String result = "";
		
		if(ret > 0) {
			
			result = "SUCCESS";
		}
		
		model.addAttribute("b_id", commentVO.getC_b_id());
		
		
		return "redirect:/comment/list";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(String c_id, Model model, String b_id) {
		
		
		int ret = commentService.delete(Long.valueOf(c_id));
		
		model.addAttribute("b_id", b_id);
		
		return "redirect:/comment/list";
	}

}
