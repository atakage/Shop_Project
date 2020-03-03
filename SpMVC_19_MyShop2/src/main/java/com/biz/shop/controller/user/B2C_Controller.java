package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/user/product")
@Slf4j
public class B2C_Controller {

	private final ProductService proService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		
		List<ProductVO> proList = proService.selectAll();
		
		model.addAttribute("B2C_LIST", proList);
		log.debug("list의 id는? " + proList);
		
		return "users/user_product_list";
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(Model model, @RequestParam("id")long id) {
		
		ProductVO proVO = proService.findById(id);
		log.debug("여기는 DETAIL" + proVO.getId());
		model.addAttribute("B2C_DTO", proVO);
		
		return "users/user_product_detail";
	}
	
}
