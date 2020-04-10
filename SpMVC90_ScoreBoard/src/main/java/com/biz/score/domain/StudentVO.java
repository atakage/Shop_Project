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
public class StudentVO {
	
	private String st_num;// varchar(4) PK 
	private String st_name;// varchar(3) 
	private int st_class;// int(2) 
	private String st_grouptbl_student;// varchar(2)

}
