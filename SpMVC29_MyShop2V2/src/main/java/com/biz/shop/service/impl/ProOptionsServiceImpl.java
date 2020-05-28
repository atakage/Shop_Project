package com.biz.shop.service.impl;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.shop.domain.ProColorVO;
import com.biz.shop.domain.ProOptionsVO;
import com.biz.shop.domain.ProSizeVO;
import com.biz.shop.persistence.DDL_Dao;
import com.biz.shop.persistence.ProOptionsDao;
import com.biz.shop.persistence.sql.CreateTableSQL;
import com.biz.shop.service.ProOptionsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ProOptionsServiceImpl implements ProOptionsService{

	private final ProOptionsDao proOPTDao;
	private final DDL_Dao ddl_Dao;
	
	
	public ProOptionsServiceImpl(ProOptionsDao proOPTDao, DDL_Dao ddl_Dao) {
		this.proOPTDao = proOPTDao;
		this.ddl_Dao = ddl_Dao;
		
		ddl_Dao.create_table(CreateTableSQL.drop_options_table);
		ddl_Dao.create_table(CreateTableSQL.create_options_table);
		ddl_Dao.create_table(CreateTableSQL.insert_options_item);
	}
	
	
	@Override
	public List<ProOptionsVO> getColorList() {
		// TODO Auto-generated method stub
		
		
		
		return proOPTDao.getColorList();
	}

	@Override
	public List<ProOptionsVO> getSizeList() {
		// TODO Auto-generated method stub
		return proOPTDao.getSizeList();
	}


	
	
	@Override
	public int getProSize(ProSizeVO proSizeVO) {
		// TODO Auto-generated method stub
		
		return proOPTDao.getProSize(proSizeVO);
	}
	
	

	
	
	@Override
	public int insert_size(ProSizeVO proSizeVO) {
		// TODO Auto-generated method stub
		return proOPTDao.insert_size(proSizeVO);
	}


	@Override
	public Object insert_color(ProColorVO proColorVO) {
		// TODO Auto-generated method stub
		

		int ret = proOPTDao.getProColor(proColorVO);
		
		log.debug("RET: " + ret);
		// 이미 DB에 등록이 되어 있으면
		if(ret > 0) {
			return "EXISTS";
		}		
		proOPTDao.insert_color(proColorVO);
		
		return proColorVO;

	}


	@Override
	public int delete_size(ProSizeVO proSizeVO) {
		// TODO Auto-generated method stub
		return proOPTDao.delete_size(proSizeVO);
	}


	@Override
	public List<ProColorVO> getColorListBySize(String s_seq) {
		// TODO Auto-generated method stub
		
			long longSeq = 0;
		try {
			longSeq = Long.valueOf(s_seq);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
		List<ProColorVO> proColorList = proOPTDao.getColorListBySize(longSeq); 
		
		
		
//		if(proColorList.size() < 1) {
//			return null;
//		}
		
		return proColorList;
	}






}
