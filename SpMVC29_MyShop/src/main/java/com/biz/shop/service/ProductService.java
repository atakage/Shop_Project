package com.biz.shop.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.web.multipart.MultipartFile;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.persistence.sql.ProductSQL;

/*
 * 상품관리 CRUD 수행할 Service
 */
public interface ProductService {

	public int insert(ProductVO productVO);
	
	public int insert(ProductVO productVO, MultipartFile file);

	public List<ProductVO> SelectAll();

	public ProductVO findById(long id);

	public ProductVO findByPCode(String p_code);

	// mysql 중간문자열 검색하기

	public List<ProductVO> findByPName(String p_name);

	public int update(ProductVO productVO);

	public int delete(String p_code);

	



}
