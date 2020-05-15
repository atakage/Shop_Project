package com.biz.tour.service.marinelife;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.biz.tour.dao.marinelife.MarineAnimalDao;
import com.biz.tour.domain.marinelife.MarineLifeAPIVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class MarineLifeAPIServiceImp implements MarineLifeAPIService {

	private final MarineAnimalDao maDao;

	@Override
	public List<MarineLifeAPIVO> getDataByCoordinates(String mapx, String mapy, String pageno, String dataLimit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	@Override
	public void getXYMarine(int mapX, int mapY) {
		// TODO Auto-generated method stub

		List<MarineLifeAPIVO> marineList = maDao.getXYMarine(mapX, mapY);
		
		// 중복 제거용
		Map<String, String> marineMap = new HashMap<String, String>();
		
		List<String> marineNameList = new ArrayList<String>();
		// mapX == latD


		// 최초 SELECT NULL일 시
		if (marineList.size() < 1 || marineList == null) {

			log.debug("NULL 진입");

			for (int i = mapX - 1; i < mapX + 2; i++) {

				for (int j = mapY - 1; j < mapY + 2; j++) {

					List<MarineLifeAPIVO> _marineList = maDao.getXYMarine(i, j);
					marineList.addAll(_marineList);

				}
			}

			for (int i = 0; i < marineList.size(); i++) {

				if (marineList.get(i) == null) {
					marineList.remove(i);
				}
				marineMap.put(marineList.get(i).getSciKr(), marineList.get(i).getSciKr());
			}
			
			
			
			marineNameList = new ArrayList<String>(marineMap.keySet());
			
			
			
			// 최초 SELECT NULL 아닐 시
		} else {

			for (int i = 0; i < marineList.size(); i++) {

				if (marineList.get(i) == null) {
					marineList.remove(i);
				}
				
				marineNameList.add(marineList.get(i).getSciKr());
			}

		}
		
		log.debug("##MARINENAMELIST: "  + marineNameList.toString());
		log.debug("##MARINENAMELISTSIZE: "  + marineNameList.size());

		log.debug("##MARINEMAP: " + marineMap.toString());
		log.debug("##MARINEMAPSIZE: " + marineMap.size());

		log.debug("##MARINELIST: " + marineList.toString());
		log.debug("##MARINELISTSIZE: " + marineList.size());
		
		
		this.callNaverAPI(marineNameList);
		

	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	

	private void callNaverAPI(List<String> marineNameList){
		// TODO Auto-generated method stub
		
		
		final String NAVER_CLIENTID = "tli3yz8GPo52_aOHiTot";
		final String NAVER_CLIENTSECRET = "x5Clo0yY24";
		
		String queryURL = "https://openapi.naver.com/v1/search/encyc.json";
		String queryString;
		
		String queryName = null;
		
		URL url;
		HttpURLConnection connection;
		
		BufferedReader br;
		InputStreamReader isr;
		
		
		String reader = null;
		StringBuffer resString = new StringBuffer();
		
		
			log.debug("NAVER API 진입: " + marineNameList.get(0).toString());
			
			
			

			try {
				
				if( marineNameList.get(0).toString().contains("_")) {
					
					log.debug("_언더바에 걸린 문자열: " + marineNameList.get(0).toString());
					int charIndex = marineNameList.get(0).toString().indexOf("_");
					queryName = marineNameList.get(0).toString().substring(0, charIndex);
					log.debug("_언더바 제거 후 문자열: " + queryName);
				}
				if( marineNameList.get(0).toString().contains("(")) {
					log.debug("(대괄호에 걸린 문자열: " + marineNameList.get(0).toString());
					int charIndex = marineNameList.get(0).toString().indexOf("(");
					queryName = marineNameList.get(0).toString().substring(0, charIndex);
					log.debug("(대괄호 제거 후 문자열: " + queryName);
				}
				
				
				queryString = URLEncoder.encode( queryName,"UTF-8");	
				queryURL = queryURL + "?query=" + queryString + "&display=" + 100 + "&start=" + 1;
				
				url = new URL(queryURL);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestProperty("X-Naver-Client-Id", NAVER_CLIENTID);
				connection.setRequestProperty("X-Naver-Client-Secret", NAVER_CLIENTSECRET);
				
				int responseCode = connection.getResponseCode();
				
				if(responseCode == 200) {
					isr = new InputStreamReader(connection.getInputStream());
					br = new BufferedReader(isr);
				}else {
					isr = new InputStreamReader(connection.getErrorStream());
					br = new BufferedReader(isr);
				}
				
				
				while(true) {
					reader = br.readLine();
					if(reader == null) {
						break;
					}
					resString.append(reader);
					
				}
				
				
				br.close();
				System.out.println("HTTP 응답 코드: " + responseCode);
				System.out.println("HTTP BODY: " + resString.toString());
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		
		
		
		
	}





	public int insertService() {
		// TODO Auto-generated method stub

		String queryURL = "http://apis.data.go.kr/B551979/marineOrganismInhabitInfoService/getHabitatGisList";
		String serviceKey = "2D5rJ2dlm%2BXKIkVprSSgKI0HU08V%2FYBLqD8l%2Furac2yM3d8LozeI%2BZJmfDX9%2BsAZY7abFzCGzXhRWQL%2BcQdgSA%3D%3D";
		Gson gSon;
		Type listType;
		List<MarineLifeAPIVO> marinList;
		JsonElement responseJsonElement;
		JsonObject responseJsonObj;
		JsonObject responseGetItem;
		JsonArray responseJsonArr;
		URL url;
		HttpURLConnection connection;
		BufferedReader br;
		String inputLine;
		StringBuffer response;
		// StringBuffer response = null;

		try {

			// for문 돌릴 시 두 번째에 JSON 파싱 에러남..... 급하니 그냥 수동으로 페이지 교체하며 저장
			// for(int pageNo = 1; pageNo < 11; pageNo++) {

			queryURL = queryURL + "?ServiceKey=" + serviceKey + "&pageNo=" + 1 + "&numOfRows=" + 2000 + "&_type=json";

			url = new URL(queryURL);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-type", "application/json");

			System.out.println("Response code: " + connection.getResponseCode());

			int responseCode = connection.getResponseCode();
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			response = new StringBuffer();

			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			br.close();

			System.out.println("HTTP 응답 코드: " + responseCode);
			System.out.println("HTTP BODY: " + response.toString());

			responseJsonElement = JsonParser.parseString(response.toString());

			responseJsonObj = responseJsonElement.getAsJsonObject();
			log.debug("과연: " + responseJsonObj.toString());

			responseGetItem = (JsonObject) responseJsonObj.get("response");
			responseGetItem = (JsonObject) responseGetItem.get("body");
			responseGetItem = (JsonObject) responseGetItem.get("items");
			responseJsonArr = (JsonArray) responseGetItem.getAsJsonArray("item");

			gSon = new Gson();

			listType = new TypeToken<ArrayList<MarineLifeAPIVO>>() {
			}.getType();

			marinList = gSon.fromJson(responseJsonArr, listType);

			maDao.insertAnimal(marinList);

			// }

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public void getAllMarine() {
		// TODO Auto-generated method stub

		List<MarineLifeAPIVO> marineList = maDao.getAllMarine();

		log.debug("MARINELIST: " + marineList.toString());

	}

	

}
