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

<header>
	<h2>Model Attribute</h2>
</header>
<section>
	
	<h3>User ID : ${userVO.userId}</h3>
	<h3>Password : ${userVO.password}</h3>
	<h3>User Name : ${userVO.userName}</h3>
	<h3>User Role : ${userVO.userRole}</h3>

</section>
</body>
</html>