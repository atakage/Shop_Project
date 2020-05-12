<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 JSP 홈페이지</title>
</head>
<body>


<div>
<form action="${rootPath}/join" method="post">
<input  name="${_csrf.parameterName }" value="${_csrf.token}">
아이디:<input name="username">
</div>
<div>
비밀번호:<input type="password" name="password">
</div>
<button>등록</button>
</form>
</body>
</html>