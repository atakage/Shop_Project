package com.biz.models.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.biz.models.domain.UsersVO;
import com.biz.models.service.UserService;

public class UserController {

	
	
	@Autowired
	protected UserService userService;
	
	
	/*
	 * 
	 * 
	 * 클래스의 method에 @ModelAttribute를 설정하고
	 * Qualifier(객체 이름 지정)를 지정해주면
	 * 이 method는 컨트롤러의 다른 method가 실행되기 전에
	 * 코드를 실행하여 객체를 model에 담아주는 일을 수행
	 * 
	 *  
	 * 컨트롤러에서 view에 rendering을 위한 데이터를 내려 보낼 때
	 * 필요할 때마다 model 생서하고
	 * model.addAttribute()를 실행해서 데이터를 보내는데
	 * 공통적으로 view에서 사용해야 할 데이터가 있을 때
	 * method에 @ModelAttribute()를 설정해 두면 view에서 해당 데이터를 가져다 rendering으로 사용 가능
	 *  
	 * 
	 * 조건이 method위에 @ModelAttribute를 설정하고 반드시 객체 이름을 지정해 주어야 함
	 * 
	 * 
	 */
	
	
	
	
	@ModelAttribute("userVO") // return 객체 값과 이름 달리해도 ?
	public UsersVO getUser() {
		

		
		
		
		return userService.getUser("admin");
	}
	
}
