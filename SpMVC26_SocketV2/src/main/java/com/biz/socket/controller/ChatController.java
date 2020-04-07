package com.biz.socket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.biz.socket.domain.MessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/*
 * 
 * 
 * LifeCycle Method
 * 어떤 동작이 수행되는 과정에서 자동으로 호출되는 method들
 * LifeCycle Method를 잘 구현함으로써 별도 어떤 동작에 해당하는 복잡한 코드를 만들지 않아도 충분한 효과를 발휘
 * 
 * afterConnectionEstablished
 * 서버와 클라이언트가 서로 Socket으로 연결되었을 때 호출되는 method session을 별도로 저장하거나, 
 * 
 * handleTextMessage
 * 클라이언트에서 메시지를 보내면 메시지를 수신하고 연산코드를 수행한 후 그 결과를 다시 클라이언트에게 보내는 코딩
 * nodejs등 다른 서버에서는 메시지별로 별도로 method를 독립적으로 작성하기도 함
 * socket.on('메시지', callback)
 * 
 * afterConnectionClosed
 * 클랑이ㅓㄴ트와 연결이 정상, 비정상적으로 종료되었을 때 수행할 코딩
 * 
 */
@Slf4j
@Component
public class ChatController extends TextWebSocketHandler {

	List<WebSocketSession> sessionList;
	Map<String, MessageVO> messageMap;

	public ChatController() {

		sessionList = new ArrayList<WebSocketSession>();
		messageMap = new HashMap<String, MessageVO>();

	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		sessionList.add(session);
		
		
		// 최초 어떤 사용자가 접속하면 사용자에 대한 메시지 정보를 담을 변수 초기화
		// 세션 id를 key값으로 하는 비어있는 사용자 정보를 map에 저장
		MessageVO mVO = new MessageVO();
		messageMap.put(session.getId(), mVO); // getId가 key값 value로 비어있는 mVO

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
		sessionList.remove(session);
		messageMap.remove(session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);

		ObjectMapper objMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();

		// 여기서부터는 임의의 command protocol을 선언
		// 절달받은 메시지에 commend가 포함되어 있는가를 구분하는 코드
		String user[] = message.getPayload().split(":");

		log.debug("메시지 페이로드: " + message.getPayload());
		log.debug("유저:" + user[0]);

		
		// userName: 또는 getUserList:
		if (user.length > 1) {

			// 채팅어플에 접속했을 때 최초 사용자 이름을 입력하면 사용자 이름과 session을 정보에 저장한 후 다시 클라이언트에게 알려주는 부분
			if (user[0].equalsIgnoreCase("USERNAME")) {

				MessageVO mVO = messageMap.get(session.getId());
				mVO.setUserName(user[1]);

				map.put("msg", "userName");
				map.put("userName", mVO.getUserName());

				String userName = objMapper.writeValueAsString(map);
				this.sendMessage(session, userName);

				// session.sendMessage(new TextMessage(objMapper.writeValueAsString(map)));

				return;

				// 현재 접속자 정보를 모두 클라이언트로 보내기
			} else if (user[0].equalsIgnoreCase("GETUSERLIST")) {

				String userList = objMapper.writeValueAsString(messageMap);
				map.put("userList", userList);
				map.put("msg", "userList");

				String userListMap = objMapper.writeValueAsString(map);
				
				log.debug(userListMap);

				this.sendAllMessage(session, userListMap);

				return;

			}

		}

//		log.debug("진입 " + session.getId() +"," + message);
//		log.debug("세션리스트 "+sessionList.toString());
//		
//		
//		
//		
//		if(message.getPayload().equalsIgnoreCase("GETUSERLIST")) {

		
		
		
		//채팅이 진행되는 과정에서 메시지 전파
		Gson gson = new Gson();

		// Gson을 사용하여 문자열형 JSON데이터를 VO로 변환
		MessageVO messageVO = gson.fromJson(message.getPayload(), MessageVO.class);

		String sendMessage = String.format("%s로부터: %s", messageVO.getUserName(), messageVO.getMessage());
		log.debug(sendMessage);

		// TextMessage sendTextMessage = new TextMessage(sendMessage);

		// jackson Bind의 클래스 ObjectMapper를 사용하여 VO클래스를 Json문자열로 바로 변환시킴

		String jSonTextMessage = objMapper.writeValueAsString(messageVO);

		
		
		if(messageVO.getToUser().equalsIgnoreCase("ALL")) {
			// 나를 제외한 전체에게 메시지 보내기
			this.sendNotMeMessage(session, jSonTextMessage);
		}else {
			
			// 전체가 아니면 전송된 session id값을 sessionList에서 조회하여 일치하는 값이 있으면 해당 접속자에게만 메시지 보내기
			for(WebSocketSession ws:sessionList) {
				if(ws.getId().equalsIgnoreCase(messageVO.getToUser())){
					this.sendMessage(ws, jSonTextMessage);
					break;
				}
			}
			
			
		}
				
				
				
		
		

		

	}



	// 요청한 접속자에게만 메시지 보내기
	private void sendMessage(WebSocketSession session, String textMessage) throws IOException {

		TextMessage sendMessage = new TextMessage(textMessage);
		session.sendMessage(sendMessage);

	}

	// 무조건 전체 접속자에게 메시지 보내기
	private void sendAllMessage(WebSocketSession session, String textMessage) throws IOException {

		TextMessage sendMessage = new TextMessage(textMessage);
		for (WebSocketSession ws : sessionList) {

			// 자신이 보낸 메시지는 다시 자신에게 보낼 필요 없기 때문에 자신이 보낸 미시지를 제외하고 전송

			log.debug("wsid" + ws.getId());
			log.debug("sesionid" + session.getId());

			ws.sendMessage(sendMessage);
		}

	}

	// 현재 접속자를 제외한 나머지 접속자에게 메시지 보내기
	private void sendNotMeMessage(WebSocketSession session, String textMessage) throws IOException {

		TextMessage sendMessage = new TextMessage(textMessage);
		for (WebSocketSession ws : sessionList) {

			// 자신이 보낸 메시지는 다시 자신에게 보낼 필요 없기 때문에 자신이 보낸 미시지를 제외하고 전송

			log.debug("wsid" + ws.getId());
			log.debug("sesionid" + session.getId());
			if (!ws.getId().equals(session.getId())) {
				ws.sendMessage(sendMessage);

			}

		}

	}

}
