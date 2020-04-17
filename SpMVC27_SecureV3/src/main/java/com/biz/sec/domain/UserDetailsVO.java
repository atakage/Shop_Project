package com.biz.sec.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Spring Security와 연동하여 회원정보를 관리하기 위한 UserDetailsVO는 단독으로 작성하지 않고
 * UserDetail이라는 interface를 implements하여 작성
 * 
 * 
 * UserVO는 인스턴스 객체를 생성할 때 생성자를 사용하여 초기값을 설정하도록 디자인
 * 
 * 
 * @update 2020-04-10
 * User 클래스를 상속받아 만든 UserVO를 UserDetails 인터페이스를 implements한 UserDetailsVO로 변경
 * 
 * UserDetails를 implements하면 method를 Override하라는 지시가 있음 하지만 method를 Override하지 않고 
 * 필드변수를 선언하고 lombok의 getter와 setter를 선언해 줌
 * 
 * 
 * 
 * 여기서 만든 UserDetailsVO는 Spring Security와 연동하여 사용자 정보를 관리할 클래스가 되고 
 * 필요에 따라 UserVO와 연동하여 데이터를 주고받기를 수행할 것임
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsVO implements UserDetails {

	/**
	 *  VO 객체를 map에 담아서 req, res에 실어서 보낼 때 객체를 문자열형으로 변환하는 과정이 있음
	 *  이 과정을 serialize라고 하는데 각 변환된 문자열이 서로 흐트러지지 않도록 설정하는 키값
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String username;
	private String password;
	private boolean enabled;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private String email;
	private String phone;
	private String address;


	
}
