package com.biz.shop.persistence.sql;

public class CreateTableSQL {
	

	
	public static String drop_options_table = "DROP TABLE IF EXISTS tbl_options";
	
	public static String create_options_table = "CREATE TABLE IF NOT EXISTS tbl_options(o_seq bigint AUTO_INCREMENT PRIMARY KEY,"
			+ "o_division VARCHAR(6) NOT NULL, o_standard VARCHAR(6) NOT NULL,"	// 상품 테이블과 연동
			+ " o_name VARCHAR(20) NOT NULL)";
	
	public static String insert_options_item = "INSERT INTO tbl_options(o_division, o_standard, o_name)VALUES('SIZE','S','Small'),('SIZE','M','Middle'),('SIZE','L','Large'),('SIZE','XL','Extra Large'),('SIZE','2XL','2Extra Large'),('SIZE','3XL','3Extra Large')"
			+ ",('COLOR','WHITE','흰색'),('COLOR','BLACK','검정'),('COLOR','BLUE','파랑')";
	
	
	public static String create_pro_size_table = "CREATE TABLE IF NOT EXISTS tbl_pro_size(s_seq bigint AUTO_INCREMENT PRIMARY KEY,"
			+ "p_code VARCHAR(6) NOT NULL,"	// 상품 테이블과 연동
			+ " s_size VARCHAR(20) NOT NULL)";
	public static String create_pro_color_table= "CREATE TABLE IF NOT EXISTS tbl_pro_color(c_seq bigint AUTO_INCREMENT PRIMARY KEY,"
			+ " size_seq bigint NOT NULL,"			// size table s_seq와 연동
			+ " c_color VARCHAR(20) NOT NULL, c_qty int default 0)";

}
