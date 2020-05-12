package com.biz.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.shop.domain.AuthorityVO;
import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.persistence.AuthoritiesDao;
import com.biz.shop.persistence.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	private final AuthoritiesDao authDao;

	public int insert(UserDetailsVO userVO) {
		// TODO Auto-generated method stub
		
		String encPassword= passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		
		int ret = userDao.insert(userVO);
		
		if(ret > 0) {
			
			
			List<AuthorityVO> authList = new ArrayList();
			
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
			
			
			int ret2 = authDao.insert(authList);
			return ret2;
		}
		
		return 0;
	}

	public int checkId(String username) {
		// TODO Auto-generated method stub
		
		
		
		
		return userDao.checkId(username);
	}

	
	
}
