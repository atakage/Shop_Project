package com.biz.tour.service.marinelife;

import java.util.List;

import com.biz.tour.domain.marinelife.MarineLifeAPIVO;

public interface MarineLifeAPIService {

	List<MarineLifeAPIVO> getDataByCoordinates(String mapx, String mapy, String pageno, String dataLimit);
	
	// 해양생물 API의 데이터를 DB에 넣는 작업
	int insertService();

	void getAllMarine();

	void getXYMarine(int mapX, int mapY);
	
	
}
