package com.biz.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository pDao;
	
	public void save(ProductVO productVO) {
		
		ProductVO p = pDao.save(productVO);
		
		log.debug("상품정보 : " + p.toString());
		
	}
	
	public List<ProductVO> selectAll(){
		
		List<ProductVO> proList = pDao.findAll();
		
		return proList;
	}

	public ProductVO findByPCode(String p_code) {

		return null;
	}

	public ProductVO findById(long id) {

		/*
		 * hibernate의 기본 조회 method는
		 * 모든 VO ㅋ르래스의 Optional클래스로 감싸서 return
		 * 이것은 혹시 모를 NullPointException을 방지하기 위한 조치이다.
		 * 실제 VO 객체를 추출할 때는 ret.get()를 사용한다. 
		 */
		Optional<ProductVO> proVO = pDao.findById(id);
		
		return proVO.get();
	}

}
