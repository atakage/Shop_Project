<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>






<style>
form {
	text-align: center;
	width: 40%;
	margin: auto;
	border-radius: 20px;
	padding: 10px;
	border: 1px solid;
}

.form-control {
	width: 70%;
	margin: auto;
}
</style>


<script>

$(function(){
	
	
	$(document).on('click', '#btn-join', function(){
		
		
		//유효성 검사
		// id, password가 입력되지 않았을 때 경고
		
		let username = $('#username')
		let password = $('#password')
		let re_password = $('#re_password')
		
		if(username.val() == ''){
			alert('아이디를 입력하세요')
			username.focus()
			return false
		}
		
		if(password.val() == ''){
			alert('비밀번호를 입력하세요')
			password.focus()
			return false
		}
		
		if(re_password.val() == ''){
			alert('비밀번호를 다시 입력하세요')
			re_username.focus()
			return false
		}
		
		if(password.val() != re_password.val()){
			alert('비밀번호와 비밀번호 확인이 다릅니다')
			password.focus()
			return false
		}
		
		$('form').submit()
		
	})
	
	
	
	// 현재 입력박스에서 포커스가 벗어났을 때 발생하는 이벤트
	$(document).on('blur', '#username', function(){
		
		let username = $(this).val()
		if(username == ""){
			$('#m_username').text('아이디는 반드시 입력해야 합니다')
			return false;
		}
		
		$.ajax({
			
			url: '${rootPath}/user/idcheck', method : 'get', data : {username:username},
			success : function(result){
				
				if(result == "USE"){
					$('#m_username').text('이미 가입된 사용자 이름입니다')
					$('#m_username').css('color', 'red')
					return false;
				}
				
				
			}, error:function(){
				
				// alert('서버와 통신 오류')
			}
			
		})
		
	})
	
	
})


</script>


</head>
<body>
	<form:form method="POST" action="${rootPath}/user/join">

		<h2
			style="font-style: italic; padding: 10px; text-decoration: underline;">회원가입</h2>
		<!-- 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">  -->
		<input id="username" name="username"
			class="form-control form-control-sm" placeholder="User ID">
			<div id="m_username" style="padding: 3px; width: 70%; margin: auto; font-size: 0.3rem; color: chocolate;"></div>
		<input type="password" id="password"
			class="form-control form-control-sm" name="password"
			placeholder="비밀번호">
		<input type="password" id="re_password"
			class="form-control form-control-sm" name="re_password"
			placeholder="비밀번호확인">
		<div style="padding: 30px;">
			<button type="button" id="btn-join" class="btn btn-primary">회원가입</button>
			<button type="button" id="btn-loss" class="btn btn-info">id/비밀번호찾기</button>
		</div>
	</form:form>
</body>
</html>