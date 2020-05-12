package com.biz.tour.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.biz.tour.domain.MarineAnimalVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import lombok.extern.slf4j.Slf4j;




/*
 * 	
 * 
 * FishAPIController의 detail method에서 좌표값을 받아와
 * 
 * 이곳에서 산출한 해양생물의 좌표값과 매칭시켜
 * 
 * 해당되는 해양생물의 데이터를 FishAPIController로 다시 return해준다
 * 
 * 
 */
@Slf4j
@Service
public class MarineAnimalService {

	public JsonArray getAnimals() throws ParseException {
		// TODO Auto-generated method stub
		
		String queryURL = "http://apis.data.go.kr/B551979/marineOrganismInhabitInfoService/getHabitatGisList";
		String serviceKey = "2D5rJ2dlm%2BXKIkVprSSgKI0HU08V%2FYBLqD8l%2Furac2yM3d8LozeI%2BZJmfDX9%2BsAZY7abFzCGzXhRWQL%2BcQdgSA%3D%3D";
		
		StringBuffer response = null;
	
		try {
			
			queryURL = queryURL + "?ServiceKey=" + serviceKey + "&pageNo=" + 1 + "&numOfRows=" + 2 + "&_type=json";
			
			
			URL url = new URL(queryURL);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-type", "application/json");
			
			System.out.println("Response code: " + connection.getResponseCode());
			
			int responseCode = connection.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			br.close();
			
			System.out.println("HTTP 응답 코드: " + responseCode);
			System.out.println("HTTP BODY: " + response.toString());
			
			
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 	JsonObject responseJsonObj = this.strToJson(response.toString());
		
	 	
		
		return null;
	}



	public JsonObject strToJson(String response) throws ParseException {

		 JsonElement responseJsonElement = JsonParser.parseString(response);
		 
		JsonObject responseJsonObj = responseJsonElement.getAsJsonObject();
		 log.debug("과연: "  + responseJsonObj.toString());
		
		 JsonObject responseGetItem = (JsonObject) responseJsonObj.get("response");
		 responseGetItem = (JsonObject) responseGetItem.get("body");
		 responseGetItem = (JsonObject) responseGetItem.get("items");
		 JsonArray responseJsonArr = responseGetItem.getAsJsonArray("item");
		 
	
		 	
		 
		 	
		 	
		 	Gson gSon = new Gson();
		 	
		 	Type listType = new TypeToken<ArrayList<MarineAnimalVO>>(){}.getType();
	 	
		
		 	
		 	List<MarineAnimalVO> marinList = gSon.fromJson(responseJsonArr, listType);
		 	
		 	log.debug("ABCD:" + marinList.toString());
		 	
		 	
		 
		 return responseJsonObj;
		
	}
	
	
	
}
