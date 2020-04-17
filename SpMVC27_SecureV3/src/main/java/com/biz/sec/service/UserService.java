package com.biz.sec.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.domain.UserVO;
import com.biz.sec.persistence.UserDao;
import com.mysql.cj.log.Log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	
	// @Autowired
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	
	
	
	
	public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		
		
		String create_user_table = " CREATE TABLE IF NOT EXISTS tbl_users(" + 
				"	id bigint PRIMARY KEY auto_increment," + 
				"	user_name varchar(50)  UNIQUE," + 
				"    user_pass varchar(125)," + 
				"    enabled boolean default true, " +
				"    email varchar(50)," + 
				"    phone varchar(20)," + 
				"    address varchar(125)" + 
				")";
		
		
		String create_auth_table = "CREATE TABLE IF NOT EXISTS authorities (" + 
				"  id bigint(20) PRIMARY KEY AUTO_INCREMENT," + 
				"  username varchar(50)," + 
				"  authority varchar(50)" + 
				")";
		
		
		
		userDao.create_table(create_user_table);
		userDao.create_table(create_auth_table);
		
		
	}
	
	
	



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
		UserDetailsVO userVO = userDao.findByUserName(username);
		if(userVO != null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		
		return false;
	}






	public UserDetailsVO findById(long id) {
		
		UserDetailsVO userVO = userDao.findById(id);
		
		// TODO Auto-generated method stub
		return userVO;
	}






	public boolean check_password(String password) {
		// TODO Auto-generated method stub
		
		
		UserDetailsVO userVO = (UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		
		
		return passwordEncoder.matches(password, userVO.getPassword());
	}






	public int update(UserDetailsVO userVO) {
		// TODO Auto-generated method stub
		
		int ret = userDao.update(userVO);
		return ret;
	}
	
}
