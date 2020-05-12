package com.biz.shop.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.AuthorityVO;

public interface AuthoritiesDao {

	@Select("SELECT * FROM authorities WHERE username = #{username}")
	List<AuthorityVO> findByUserName(String username);

	int insert(List<AuthorityVO> authList);

	
	
	
}
