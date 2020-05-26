<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:set var="rootPath" value="${pageContext.request.contextPath}"/>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 JSP 홈페이지</title>
</head>
<body>

<form action="${rootPath}/login" method="post">

<div>
<input name="${_csrf.parameterName}" value="${_csrf.token}">
</div>

아이디:<input name="username">
비밀번호:<input type="password" name="password">
<button>로그인하기</button>

</form>

</body>
</html>