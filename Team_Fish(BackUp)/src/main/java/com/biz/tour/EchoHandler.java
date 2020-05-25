package com.biz.tour;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler{
	
	private List<WebSocketSession> socketSessionList = new ArrayList<WebSocketSession>();

	
	// 클라이언트 연결되었을 때
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
		// TODO Auto-generated method stub
		socketSessionList.add(session);
		System.out.printf("{%s연결됨}",session.getId());
	}

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.printf("{%s}로부터 {%s}받음", session.getId(), message.getPayload());
		
		// 모든 유저에게 메시지 출력
		for(WebSocketSession sess : socketSessionList) {
			sess.sendMessage(new TextMessage(message.getPayload()));
		}
	}

	
	// 클라이언트 연결 끊었을 때 실행됨
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		socketSessionList.remove(session);
		System.out.printf("{%s} 연결 끊김", session.getId());
	}
	
	
	
	
	
}
