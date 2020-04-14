<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>






<head>
<%@ include file="/WEB-INF/views/include//include-head.jspf" %>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>




<style>



	


	body{
	
		margin-top: 5%;
		
	
	}
	
	

</style>

</head>
<body>













<div>MY PAGE</div>


<div id="infoDiv">


아이디:${PRINCIPAL.principal.username}<br>
이메일:${PRINCIPAL.principal.email}<br>
전화번호:${PRINCIPAL.principal.phone}<br>
주소:${PRINCIPAL.principal.address}<br>


</div>


</body>
</html>