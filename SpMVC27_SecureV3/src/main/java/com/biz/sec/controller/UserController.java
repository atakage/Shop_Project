package com.biz.sec.controller;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;

	
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
	@RequestMapping(value="/password", method=RequestMethod.POST)
	public String password(String password) {
		
		log.debug("패스워드:" + password);
		boolean ret = userService.check_password(password);
		log.debug("체크 결과:" + ret);
		if(ret) return "PASS_OK";
		return "PASS_FAIL";
		
		
		
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
		
		UsernamePasswordAuthenticationToken upa = (UsernamePasswordAuthenticationToken) principal;
		
		UserDetailsVO userVO = (UserDetailsVO)upa.getPrincipal();
		
		model.addAttribute("userVO", userVO);
		
		return "auth/user_view";
	}
	
	
	@RequestMapping(value="/mypage", method=RequestMethod.POST)
	public String mypage(Model model, UserDetailsVO userVO) {
	
		
		Authentication oldAuth = SecurityContextHolder.getContext().getAuthentication();
		
		log.debug("oldAuth: "+ oldAuth.toString());
		
		int ret = userService.update(userVO);
		
		if(ret > 0) {
			Authentication newAuth = new UsernamePasswordAuthenticationToken((Principal)userVO, oldAuth.getCredentials());
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		
		return "redirect:/user/mypage";
		
	}
	
}
