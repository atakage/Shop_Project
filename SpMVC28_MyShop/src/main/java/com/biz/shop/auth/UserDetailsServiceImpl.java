package com.biz.shop.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.shop.domain.AuthorityVO;
import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.persistence.AuthoritiesDao;
import com.biz.shop.persistence.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	
	private final AuthoritiesDao authDao;
	private final UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		UserDetailsVO userVO = userDao.findByUserName(username);
		
		if(userVO == null) {
			throw new UsernameNotFoundException("유저 네임이 없습니다");
		}
		
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setCredentialsNonExpired(true);
		userVO.setAuthorities(this.getAuthorities(username));
		
		log.debug("USERVO: " + userVO.toString());
		
		return userVO;
	}

	private Collection<GrantedAuthority> getAuthorities(String username) {
		// TODO Auto-generated method stub
		
		List<AuthorityVO> authList = authDao.findByUserName(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(AuthorityVO vo : authList) {
			SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(vo.getAuthority());
			authorities.add(sAuth);
			
		}
		
		return authorities;
	}

}
