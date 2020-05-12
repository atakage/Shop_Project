package com.biz.shop.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biz.shop.domain.UserDetailsVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthProviderImpl implements AuthenticationProvider{

	
	
	@Autowired
	private UserDetailsService userDService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		log.debug("네임: " + authentication.getPrincipal());
		
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		
		UserDetailsVO userVO = (UserDetailsVO) userDService.loadUserByUsername(username);
		
		log.debug("PASSWORD: " + password);
		log.debug("USERVO: " + userVO.toString());
		
		log.debug("PASSWORDENCODER: " + passwordEncoder.toString());
		
		if(!passwordEncoder.matches(password.trim(), userVO.getPassword().trim())) {
			throw new BadCredentialsException("비밀번호 오류");
		}
		
		if(!userVO.isEnabled()) {
			throw new BadCredentialsException("접근권한 없음");
		}
		
		return new UsernamePasswordAuthenticationToken(userVO, null, userVO.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	
}
