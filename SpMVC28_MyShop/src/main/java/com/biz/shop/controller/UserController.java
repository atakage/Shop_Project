package com.biz.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class UserController {

	
	private final UserService userService;
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(String username, String password) {
		
		
		
		int ret = userService.checkId(username);
		
		if(ret > 0) {
			
			log.debug("아이디 이미 있음");
			return "redirect:/";
			
		}
		
		
		// 아이디 중복체크 하나만
		
		log.debug("ㅇㅇ: " + username +"," + password);
		
		UserDetailsVO userVO = UserDetailsVO.builder().username(username).password(password).build();

		int ret2 = userService.insert(userVO);
		
		
		log.debug("등록 성공");
		
		
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value="/mypage", method = RequestMethod.GET)
	public String mypage(Authentication authentication) {
		
	}
	
	
}
