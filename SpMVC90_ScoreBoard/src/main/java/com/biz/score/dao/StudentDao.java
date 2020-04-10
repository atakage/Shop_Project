package com.biz.score.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.score.domain.ScoreVO;
import com.biz.score.domain.StudentVO;

public interface StudentDao {

	@Select("SELECT MAX(st_num) FROM tbl_student")
	int getMaxStNum();

	@Insert("INSERT INTO tbl_student(st_num, st_name, st_class, st_grouptbl_student) VALUES(#{st_num}, #{st_name}, #{st_class}, #{st_grouptbl_student})")
	int insert(StudentVO studentVO);

	@Select("SELECT st_name FROM tbl_student WHERE st_num = #{s_num}")
	String findStNameBySNum(String s_num);

	@Insert("INSERT INTO tbl_score(s_num, s_korean, s_english, s_math, s_total, s_average) VALUES(#{s_num}, #{s_korean}, #{s_english}, #{s_math}, #{s_total}, #{s_average})")
	int insertScore(ScoreVO scoreVO);

	
	
}
