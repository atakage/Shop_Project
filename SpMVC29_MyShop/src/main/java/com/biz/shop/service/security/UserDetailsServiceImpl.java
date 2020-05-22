package com.biz.shop.service.security;

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
import com.biz.shop.persistence.AuthorityDao;
import com.biz.shop.persistence.DDL_Dao;
import com.biz.shop.persistence.UserDao;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	private final UserDao userDao;
	private final AuthorityDao authDao;
	private final DDL_Dao ddl_dao;
	
	public UserDetailsServiceImpl(UserDao userDao, AuthorityDao authDao, DDL_Dao ddl_dao) {
		this.userDao = userDao;
		this.authDao = authDao;
		this.ddl_dao = ddl_dao;
		
		// 테이블 생성 부분을 코딩하기 위한 방법
		
		
		
		String create_user_table = " CREATE TABLE IF NOT EXISTS tbl_users ("
				+ "	id bigint  PRIMARY KEY AUTO_INCREMENT, " + "	user_name varchar(50) UNIQUE, "
				+ "	user_pass varchar(125), " + "   enabled boolean default true, " + "	email varchar(50), "
				+ "	phone varchar(20), " + "	address varchar(125) " + " ) ";

		String create_auth_table = " CREATE TABLE IF NOT EXISTS authorities ("
				+ "	id bigint PRIMARY KEY AUTO_INCREMENT," + "    username varchar(50)," + "    authority varchar(50)"
				+ " ) ";

		
		ddl_dao.create_table(create_user_table);
		ddl_dao.create_table(create_auth_table);
		
	}
	
	/*
	 * tbl_users 테이블로부터 username, password, enabled값을 가져와서 UserDetailsVO에 담기
	 * loadUserByUsername() method는 Authen, Provider에서 호출하여 로그인한 사용자 정보를 가져감
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserDetailsVO userVO = userDao.findByUserName(username);
		
		if(userVO == null) {
			throw new UsernameNotFoundException(username + "정보를 찾을 수 없음");
		}
		
		userVO.setAccountNonLocked(true);
		userVO.setAccountNonExpired(true);
		userVO.setCredentialsNonExpired(true);
		
		// username에 해당하는 ROLE List 추출하여 vo에 세팅
		userVO.setAuthorities(this.getAuthorities(username));
		return userVO;
	}

	
	/*
	 * authorities 테이블에서 ROLE 정보를 가져와서 UserDetailsVO와 합성하기 위한 준비를 수행하는 method
	 */
	
	
	private Collection<GrantedAuthority> getAuthorities(String username){
		//	1. authorities 테이블에서 username으로 조회한 ROLE LIST를 추출
		
		List<AuthorityVO> authList = authDao.findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// List<AuthorityVO> 리스트를 List<GrantedAuthority>로 변환
		
		for(AuthorityVO vo : authList) {
			
			SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(vo.getAuthority());
			authorities.add(sAuth);
		}
		
		return authorities;
	}
}
