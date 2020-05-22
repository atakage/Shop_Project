package com.biz.shop.persistence.sql;

public class CreateTableSQL {
	

	
	
	public static String create_pro_size_table = "CREATE TABLE IF NOT EXISTS tbl_pro_size(s_seq bigint AUTO_INCREMENT PRIMARY KEY,"
			+ "p_code VARCHAR(6) NOT NULL,"	// 상품 테이블과 연동
			+ " s_size VARCHAR(20) NOT NULL)";
	public static String create_pro_color_table= "CREATE TABLE IF NOT EXISTS tbl_pro_color(c_seq bigint AUTO_INCREMENT PRIMARY KEY,"
			+ " size_seq bigint NOT NULL,"			// size table s_seq와 연동
			+ " c_color VARCHAR(20) NOT NULL, c_qty int default 0)";

}
