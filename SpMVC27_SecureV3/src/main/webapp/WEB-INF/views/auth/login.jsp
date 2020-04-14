<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<!-- Latest compiled and minified CSS -->
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
	width: 20%;
	margin: auto;
	margin-top: 10%;
	background-color: crimson;
	border-radius: 20px;
	padding: 10px;
	
}

button {
	border: none;
	background-color: crimson;
}

.form-control {
	width: 50%;
	margin: auto;
}
</style>


</head>

<script>

$(function(){
	
	$(document).on('click','button.join', function(){
	
	document.location.href="${rootPath}/user/join"	
		
	})
	
	
	
})

</script>


<body>
	<form:form method="POST" action="${rootPath}/login">

		<h2 style="color: white; font-weight: bold;">로그인</h2>

		<div>
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				
				<span>${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
				
			</c:if>
		</div>

		<input id="username" class="form-control form-control-sm"
			name="username" placeholder="User ID"> <input type="password"
			id="password" class="form-control form-control-sm" name="password"
			placeholder="비밀번호">

		<button class="btn btn-danger">로그인</button>
		<button class="btn btn-danger join" type="button">회원가입</button>

	</form:form>
</body>
</html>