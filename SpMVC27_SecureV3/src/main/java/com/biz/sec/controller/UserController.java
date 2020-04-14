package com.biz.sec.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.service.UserService;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	
	private final UserService userService;

	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "auth/login";
		
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		
		return "auth/join";
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/join", method=RequestMethod.POST, produces = "text/html;charset=utf8")
	public String join(String username, String password) {
		
		
		log.debug("아이디 {} " , username);
		log.debug("비밀번호 {} " , password);
		
		
		userService.insert(username, username);
		
		return String.format("아이디 <b>%s</b>, 비번 <b>%s</b>", username, password);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck", method=RequestMethod.GET )
	public String idCheck(String username) {
		
		boolean ret = userService.isExistUserName(username);
		
		if(ret == true) {
			return "EXISTS";
		}

		return "NonExists".toUpperCase(); // NONEXISTS
	}
	
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.GET)
	public String user() {
		
		return "user home";
	
	}
	
	
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(Model model, Principal principal) {
		
		
		//UserDetailsVO userVO = userService.findById(id);
		//model.addAttribute("userVO", userVO);
		
		
		model.addAttribute("PRINCIPAL", principal);
		
		return "user/mypage";
	}
}
