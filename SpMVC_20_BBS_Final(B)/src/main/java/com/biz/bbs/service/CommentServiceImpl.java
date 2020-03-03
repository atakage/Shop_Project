package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.repository.CommentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
	
	
	private final CommentDao commentDao;
	

	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentVO findById(long c_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentVO> findByPId(long c_p_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(CommentVO commentVO) {
		// TODO Auto-generated method stub
		
		
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		commentVO.setC_date_time(ldt.format(dtf).toString());
		
		return commentDao.insert(commentVO);
	}

	@Override
	public int update(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long c_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CommentVO> findByBId(long b_id) {
		// TODO Auto-generated method stub
		
		
		
		
		
		return commentDao.findByBId(Long.valueOf(b_id));
	}

	
	
	
}
