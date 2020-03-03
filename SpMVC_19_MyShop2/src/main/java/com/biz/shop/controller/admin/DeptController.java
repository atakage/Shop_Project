package com.biz.shop.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.service.DeptService;

import lombok.RequiredArgsConstructor;

@SessionAttributes("deptVO")
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dept")
@Controller
public class DeptController {

	private final DeptService dService;

	// */admin/dept로 맵핑
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String input(Model model) {

		this.modelMapping(model);
		
		DeptVO deptVO = new DeptVO();
		model.addAttribute(deptVO);

		return "admin/main";
	}

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String input(@Valid @ModelAttribute DeptVO deptVO, BindingResult result, Model model) {

		if (result.hasErrors()) {
			this.modelMapping(model);
			model.addAttribute("deptVO", deptVO);
			return "admin/main";

		}

		DeptVO ret = dService.save(deptVO);
		
		return "redirect:/admin/dept";
	}

	@RequestMapping(value= {"/search/{search}","/search/","/search"},method=RequestMethod.GET)
	public String search(Model model,
						@PathVariable(name="search", required= false) String search) {
		
		this.modelMapping(model, search);
		
		return "admin/dept_list";
	}
	
	private void modelMapping(Model model, String search) {
		
		List<DeptVO> deptList = null;
		
		if(search == null) {
			deptList = dService.selectAll();	
		}else {
			deptList = dService.findByDName(search);
		}
		model.addAttribute("DEPT_LIST", deptList);
		model.addAttribute("BODY", "DEPT");
		
	}
	
	private void modelMapping(Model model) {
		
		this.modelMapping(model, null);
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable("id")String strId, Model model, 
			@ModelAttribute("deptVO") DeptVO deptVO) {
		
		List<DeptVO> deptList = dService.selectAll();
		
		this.modelMapping(model);
		long id = Long.valueOf(strId);
		deptVO = dService.findById(id);
		
		model.addAttribute("DEPT_LIST", deptList);
		model.addAttribute("deptVO", deptVO);
		model.addAttribute("BODY","DEPT");
		
		return "admin/main";
	}

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		
		this.modelMapping(model);
		
		return "admin/dept_list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
