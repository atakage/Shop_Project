package com.biz.sec.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.utils.PbeEncryptor;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MailSendService {
	
	@Qualifier("naverMailHandler")
	private final JavaMailSender javaMailSender;
	private final String from_email = "atakoko@naver.com";

	
	
	
	public MailSendService(@Qualifier("naverMailHandler")
	JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}




	public void sendMail() {
		
		
		String to_email = "atakoko@naver.com";
		String subject = "메일보내기 테스트";
		String content = "반갑습니다";
		
		this.sendMail(to_email, subject, content);
		
	}
		
	public void sendMail(String to_email, String subject, String content) {
			
			
		
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mHelper;
		
		mHelper = new MimeMessageHelper(message,"UTF-8");
		try {
			mHelper.setFrom(from_email);
			mHelper.setTo(to_email);
			mHelper.setSubject(subject);
			mHelper.setText(content,true); // true: 메일 본문에 html 효과주기
			
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}




	/*
	 * 회원가입된 사용자에게 인증 email을 전송
	 * 
	 * username을 암호화시키고 email 인증을 수행할 수 있는 링크를 email 본문에 작성하여 전송을 함
	 *  
	 * 
	 * @param userVO
	 * @return
	*/
	
	//public boolean join_send(UserDetailsVO userVO) {
	
	
	public String join_send(UserDetailsVO userVO) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		
		
		
		
		String userName = userVO.getUsername();
		String email = userVO.getEmail();
		String encUserName = PbeEncryptor.getEncrypt(userName);
		String encEmail = PbeEncryptor.getEncrypt(email);
		
		
		/*
		 * jasypt를 사용하여 username과 email을 암호화 했더니 슬래시(/) 등 URL을 통해 보내면 문제를 발생시키는 특수문자들이 포함됨
		 * 이 특수문자를 URL을 통해서 정상적으로 보낼 수 있도록 암호화된 문자열을 URLEncoder.encode() method를 이용해서 encoding을 수행해 주어야 함
		 */
		
		// localhost:8080/sec/join/email/callor/callor@callor.com
		StringBuilder email_link = new StringBuilder();
				email_link.append("http://localhost:8080/sec/");
				email_link.append("join/emailok");
				email_link.append("?username="+URLEncoder.encode(encUserName,"UTF-8"));

				email_link.append("&email="+URLEncoder.encode(encEmail,"UTF-8"));
				
				
				StringBuilder email_message = new StringBuilder();
				
				email_message.append("<h3>회원가입을 환영</h3><br/>");
				email_message.append("<p>회원가입 절차를 마무리하려면 email 인증을 하여야 함<br/>");
				email_message.append("<p><a href='%s'>Email 인증</a>");
				email_message.append("링크 클릭");
		
				
				
				
				String send_message = String.format(email_message.toString(), email_link.toString());
				
				log.debug("센드메시지:" + send_message);
				
				String to_email = email;
				String subject = "봄나라 회원인증 메일";
				this.sendMail(to_email, subject, send_message);
				
		return send_message;
	}

}
