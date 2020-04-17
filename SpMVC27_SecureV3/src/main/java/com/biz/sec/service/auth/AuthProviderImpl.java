package com.biz.sec.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biz.sec.domain.UserDetailsVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AuthProviderImpl implements AuthenticationProvider{

	
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDService;
	
	/*
	 * Security context에 bean으로 등록되어 있음
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*
	 * spring security에서 커스터마이징을 수행하여 로그인을 세세히 제어하고자 할 때 코드를 작성해야 하는 중요한 method
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		/*
		 * authentication으로부터 login form에서 보낸 username과 password를 추출
		 * 
		 * 
		 */
		
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		
		log.debug("입력 패스워드: " + passwordEncoder.encode(password).toString());
		
		// service -> Dao 통해서 dB로부터 사용자 정보 가져오기
		UserDetailsVO userVO = (UserDetailsVO) userDService.loadUserByUsername(username);
		
		log.debug("보관 패스워드: "+  userVO.getPassword());
		
		log.debug("PASS {}",password);
		
		log.debug("비번 매치 {}", passwordEncoder.matches(password.trim(), userVO.getPassword().trim()));
		
		
		if(!passwordEncoder.matches(password.trim(), userVO.getPassword().trim())) {
			
			// throw new BadCredentialsException("비밀번호 오류");
			
		}
		
		// enabled false이면, 사용금지된 username일 경우
		if(!userVO.isEnabled()) {
			throw new BadCredentialsException(username + ": 접근권한 없음");
		}
		
		
		
		// UserDetailService에서 보내준 사용자 정보를 Controller로 보내는 일을 수행
		return new UsernamePasswordAuthenticationToken(userVO, null, userVO.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
