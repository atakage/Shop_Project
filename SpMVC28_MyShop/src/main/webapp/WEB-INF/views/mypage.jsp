<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 JSP 홈페이지</title>
</head>
<body>


<div>[마이페이지]</div>

username<input readonly="readonly" value="${USERNAME}">
authority
<c:forEach items="${AUTHORITYLIST}" var="AUTHORITYLIST">
	<div>
		<input value="${AUTHORITYLIST.authority}"> 
	</div>
</c:forEach>

</body>
</html>