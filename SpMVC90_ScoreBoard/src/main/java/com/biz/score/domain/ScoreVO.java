package com.biz.score.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScoreVO {
	
	private String s_num;// varchar(4) PRIMARY KEY, -- 학번
	private int s_korean;// int not null,-- 과목명
	private int s_english;// int not null,-- 과목명
	private int s_math;// int not null,-- 과목명
	private int s_total;// int,
	private int s_average;// int,
	private int s_rank;// int
	

}
