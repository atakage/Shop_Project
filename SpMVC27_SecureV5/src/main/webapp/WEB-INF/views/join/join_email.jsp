<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:set var="rootPath" value="${pageContext.request.contextPath }"/>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<style>
section.email_body{
	width:80%;
	margin: 120px auto;
	flex-flow: colum;
	justify-content: center;
	align-items: center;
}


span#secret{
	display:none;
}
</style>
<script>
$(function(){
	
	$(document).on('click','#btn_email_ok',function(){
		
		let secret_key = $('span#secret').text()
		alert(secret_key)
		
		let secret_value = $('input#email_ok').val()		// 내가 입력한 값
		if(secret_value == ''){
			alert('인증 코드를 입력한 후 인증 버튼을 클릭')
			$('input#email_ok').focus()
			return false
		}
		
		$.ajax({
			url: '${rootPath}/join/email_token_check', method:'post', data:{'${_csrf.parameterName}' : '${_csrf.token}', secret_id : '${username}' , secret_key : secret_key, secret_value:secret_value },
			success : function(result){
				alert(result)
				document.location.replace('${rootPath}/user/login')
			},error:function(){
				alert('서버 오류')
			}
		})
	})
	
})
</script>
</head>

<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<section class="email_body">

	<div>
	<img src="${rootPath}/resources/image/8807.png" width="100px" height="100px">
	<h2>Email 인증</h2>
	회원가입을 완료하려면 Email 인증을 수행해야 함
	</div>
	
	
	<br>
	<br>
	<div>
	<form:form action="${rootPath}/join/join_last" modelAttribute="userVO">
	
	
	
	
	<form:input type="email" path="email" placeholder="email"/>
	
	
	<c:choose>
		<c:when test="${JOIN == 'EMAIL_OK' }">
			<button>인증 Email 다시 보내기</button>
			<p>E-mail을 열어서 인증코드를 확인한 후 아래 입력란에 입력 후 인증 버튼을 클릭하세요
			<div>
				<span id="secret">${My_Email_Secret}</span>
				<input id="email_ok">
				<button type="button" id="btn_email_ok">인증하기</button>
			</div>	
		</c:when>
		<c:otherwise>
			<button>인증 Email 보내기</button>
		</c:otherwise>
	</c:choose>
	</form:form>
	</div>
	
</section>
</body>
</html>