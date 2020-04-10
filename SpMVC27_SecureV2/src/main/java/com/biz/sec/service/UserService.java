package com.biz.sec.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserVO;
import com.biz.sec.persistence.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	
	// @Autowired
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	
	
	
	/*
	 * 회원가입을 신청하면 비밀번호는 암호화하고 
	 * 아이디와 비번을 DB에 insert
	 * 
	 * @update 20200410
	 * Map<String,String>구조의 VO데이터를 UserVO로 변경
	 */
	
	
	
	/**
	 * @since 2020-04-09
	 * @author callor
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	
	
	public int insert(String username, String password) {
		
		
		// 회원가입 form에서 전달받은 password값을 암호화시키는 과정
		
		
		
		String encPassword = passwordEncoder.encode(password);
		
		UserVO userVO = UserVO.builder().username(username).password(encPassword).build();
		
		int ret = userDao.insert(userVO);
		
		return ret;
	}



	public boolean isExistUserName(String username) {
		// TODO Auto-generated method stub
		
		
		// 이미 DB에 회원정보(username_이 저장되어 있음
		UserVO userVO = userDao.findByUserName(username);
		if(userVO != null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		
		return false;
	}
	
}
