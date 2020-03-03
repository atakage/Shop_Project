package com.biz.app.service;

import java.util.Scanner;

import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.stereotype.Service;

import com.biz.app.ScoreVO;


/*
 * 
 * Service 클래스
 * @Service Annotation을 설정한 클래스
 * 
 * Controller가 사용자의 요청을 받았는데
 * 단순한 연산을 수행해서 결과를 보낼 사안이 아닐 때 또는 복잡한 무언가를 수행해야 할 때 
 * Controller의 기능을 보조하는 역할을 수행함
 * 
 * 
 */
@Service
public class NumService {

	
	
	
	public int add(int num1, int num2) {
		
		int sum = 0;
		sum = num1 + num2;
		
		return sum;
	}

	public int even(int start, int end) {
		// TODO Auto-generated method stub
		
		int sum = 0;
		for(int i = start; i <= end; i++) {
			
			if(i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}

	public int[] addPlus() {
		// TODO Auto-generated method stub
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("국어 점수:");
		String korean = scan.nextLine();
		int intKorean = Integer.valueOf(korean);
		
		System.out.println("영어 점수:");
		String eng = scan.nextLine();
		int intEng = Integer.valueOf(korean);
		
		System.out.println("수학 점수:");
		String math = scan.nextLine();
		int intMath = Integer.valueOf(math);
		
		System.out.println("음악 점수:");
		String music = scan.nextLine();
		int intMusic = Integer.valueOf(music);
		
		
		System.out.println("과학 점수:");
		String science = scan.nextLine();
		int intScience = Integer.valueOf(science);
		
		
		
		
		int sum = intKorean + intEng + intMath + intMusic + intScience;
		
		int[] resInt = {intKorean, intEng, intMath, intMusic, intScience, sum,};
		
		return resInt;
	}

	public int sum(int kor, int eng, int math, int sci, int music) {
		// TODO Auto-generated method stub
		
		
		 
		return kor+eng+math+sci+music;
	}

	public int avg(int kor, int eng, int math, int sci, int music) {
		// TODO Auto-generated method stub
		
		int sum = kor + eng + math + sci + music;
		
		return sum/5;
	}

	public void score(ScoreVO scoreVO) {
		// TODO Auto-generated method stub
		
		int sum = scoreVO.getKor();
		sum += scoreVO.getEng();
		sum += scoreVO.getMath();
		sum += scoreVO.getSci();
		sum += scoreVO.getMusic();
		
		
		scoreVO.setSum(sum);
		scoreVO.setAvg(sum/5);
		
	}
	
	
}
