<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>
    <c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



<meta charset="UTF-8">




<style>

	section.body{
		display:flex;
	}
	
	article.menu{
		border:	1px solid blue;
		height: 800px;
	}
	
	article.menu{
		flex-basis: 300px;
	}
	
	article.content{
		border:	1px solid blue;
		height: 800px;
	}
	

</style>



</head>


<body>


<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>
<section class="container-fluid body p-2">
	<article class="container menu m-2">
		<%@ include file="/WEB-INF/views/admin/admin_nav.jspf" %>
	</article>
	<article class="container-fluid content m-2">
	
	</article>
</section>
</body>

</html>