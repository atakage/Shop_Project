<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>
    <c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>

$(function(){

	
	$(document).on('click','.logoutBtn',function(){
	
		alert('dㅇㅇ')
		
		$.ajax({
			url:"${rootPath}/logout", data: {${_csrf.parameterName} : '${_csrf.token}'}, type:"post",
			success:function(result){
				alert('성공')
				document.location.replace('${rootPath}/')
			},error:function(){
				alert('서버에러')
			}
		})
		
		
	})
	
	
})




</script>


<meta charset="UTF-8">
<title>나의 JSP 홈페이지</title>
</head>
<body>


<button type="button" onclick="document.location.href='${rootPath}/join'">회원가입</button>

<security:authorize access="isAnonymous()">
	<button type="button" onclick="document.location.href='${rootPath}/login'">로그인</button>
</security:authorize>

<security:authorize access="isAuthenticated()">
	<button type="button" class="logoutBtn" >로그아웃</button>	
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
	<button type="button" onclick="document.location.href='${rootPath}/admin'">관리자페이지</button>
</security:authorize>

</body>
</html>