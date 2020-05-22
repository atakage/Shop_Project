package com.biz.shop.persistence.sql;

import org.apache.ibatis.jdbc.SQL;

public class UserSQL {
	
	public String select_user() {
		
		SQL sql = new SQL();
		sql.FROM("TBL_USERS").SELECT("user_name_as_username").SELECT("user_pass as password").SELECT("ENABLED")
		//.SELECT("nickname")
		.SELECT("phone").SELECT("email").SELECT("address").SELECT("ID");
		
		return sql.toString();
		
	}
	
	
	public String select_user_one() {
		
		SQL sql = new SQL();
		sql.FROM("TBL_USERS").WHERE("user_name = #{username}").SELECT("user_name as username").SELECT("user_pass as password").SELECT("ENABLED")
		//.SELECT("nickname")
		.SELECT("phone").SELECT("email").SELECT("address").SELECT("ID");
		
		return sql.toString();
		
	}
	

}
