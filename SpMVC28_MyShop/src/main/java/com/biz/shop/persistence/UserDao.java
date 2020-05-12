package com.biz.shop.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.UserDetailsVO;

public interface UserDao {

	@Select("SELECT user_name as username, user_pass as password, enabled FROM tbl_users WHERE user_name = #{username}")
	UserDetailsVO findByUserName(String username);

	@Insert("INSERT INTO tbl_users(user_name,user_pass) VALUES(#{username},#{password})")
	int insert(UserDetailsVO userVO);

	@Select("SELECT COUNT(*) FROM tbl_users WHERE user_name = #{username}")
	int checkId(String username);

}
