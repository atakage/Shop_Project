package com.biz.bbs.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.biz.bbs.domain.CommentVO;

public interface CommentDao {

	
	
	public List<CommentVO> selectAll();
	public CommentVO findById(long c_id);
	
	/*
	 * 
	 * 게시판 원글에 달린 코멘트 들만 추출하기
	 * 
	 */
	public List<CommentVO> findByPId(long c_p_id);
	
	public int insert(CommentVO commentVO);
	
	
	
	public int update(CommentVO commentVO);
	
	
	@Delete("DELETE FROM tbl_comment WHERE C_ID = #{c_id}")
	public int delete(long c_id);
	
	public List<CommentVO> findByBId(long b_id);
	
	
}
