<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 JSP 홈페이지</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	/*
	
	 화면상의 DOM 객체가 모두 그려지기 전에 event 등을 설정하면 기능이 제대로 작동되지 않음
	 jquery에서 
	
	 $(function(){
	
	 코드들 
	
	 }) 
	 형식으로 작성하면 화면상의 모든 tag에 의한 DOM이 완전히 그려진 다음 코드들이 작동되어 문제를 일으키지 않음
	
	
	 */
	$(function() {

		$("button[type='button'].userId").on('click', function() {

			$.ajax({
				
				
				
				url:"sendUserId", data:$('form').serialize(), method:"POST", dataType:"json",
				success:function(result){
					
					alert(result.RET_CODE)
					if(result.RET_CODE == 'recv_OK'){
						
						let user = result.userVO.userId + "\n"
						user += result.userVO.password + "\n"
						user += result.userVO.userName + "\n"
						user += result.userVO.role + "\n"
						
						alert("사용자:   " + user)
					
					}
				},error:function(){
					alert('서버 에러')
				}
				
			})

		})
		
		
		$("button.user").click(function(){
			
			
			$.ajax({
				
				url: "sendUser", data: $('form').serialize(), method:'POST',  // form에 입력된 데이터를 통째로 json으로 변경
				success : function(userVO){
					
					
					
					// json객체 형태의 데이터는 alert로 확인하면 [Object object] 형식으로만 확인이 됨
					// 이 객체를 toString()하는 것처럼 문자열로 풀어서 나타냄
					
					alert(JSON.stringify(userVO))
					
					/*
					
						json형태로 받은 userVO의 값을 사용해서 html tag코드를 작성
					
					*/
					let html = "<p>" + userVO.userId + "</p>"
					html += "<p>" + userVO.password + "</p>"
					html += "<p>" + userVO.userName + "</p>"
					html += "<p>" + userVO.role + "</p>"
					
					
					// HTML tag 코드를 #ret_html section부분에 끼워넣기
					$('#ret_html').html(html)
					
				},error:function(){
					
					
				}
				
			})
			
		})
		
		
		
		$('button.html').click(function(){
			
			
			$.ajax({
				
				url:"html", method:"get", data: $('form').serialize(),
				success:function(result){
					
					$('#ret_html').html(result)
					
				},error:function(){
					
					alert('서버 에러')
				}
				
			})
			
			
		})

	})
</script>
</head>
<body>
	<section>
		<h2>사용자 정보</h2>
		<h5>사용자 ID : ${userVO.userId }</h5>
		<h5>비밀번호 : ${userVO.password }</h5>
		<h5>사용자 이름 : ${userVO.userName }</h5>
		<h5>사용자 권한 : ${userVO.role }</h5>
	</section>
	
	
	<section id="ret_html">
	
	
	
	
	
	
	
	
	</section>
	
	
	
	<section>

		<form action="saveUser" method="POST">

			<div>
				<input id="userId" placeholder="사용자 ID" name="userId">
			</div>
			<div>
				<input id="password" placeholder="패스워드" name="password">
			</div>
			<div>
				<input id="userName" placeholder="사용자 이름" name="userName">
			</div>
			<div>
				<input id="role" placeholder="사용자 권한" name="role">
			</div>

			<%
				/*
				
					button에 type 지정하지 않으면 기본 type="submit"이 되고 이 button이 form 안에 있을 때는 버튼을 클릭했을 때
					form 안의 input box에 입력한 값을 모두 모아서 action으로 지정된 url로 모두 전송하는 기능을 수행
					button type="button"으로 지정하면 모양만 버튼이고 기본 기능을 모두 수행하지 않도록 함
				
				*/
			%>
			
			<button type="submit">저장</button>
			<button type="button" class="userId">사용자 ID 전송</button>
			<button type="button" class="user">입력값 전송</button>
			<button type="button" class="html">입력값 html로 받기</button>
			
			
		</form>

	</section>
</body>
</html>