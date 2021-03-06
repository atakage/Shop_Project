package com.biz.ajax.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserVO implements Serializable{

	
	private String userId;
	private String password;
	private String userName;
	private String role;
	
	
	// get method는 필드변수가 있다라고 인식을 하기 때문에 jackson이 json으로 변경하는 동안에 착각하고 오류일으킬 수 있음
	public UserVO sampleVO() {
		
		UserVO userVO = UserVO.builder().userId("admin").password("12345").userName("홍길동").role("admin").build();
		
		return userVO;
	}
}
