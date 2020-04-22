<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" 
			prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  
			prefix="sec"%>
			
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>	

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>		
						


	<style>
		section{
			margin:10px auto;
			width: 70%;
		}
		
		form {
			width:100%;
			margin:10px auto;
		}
		
		
		form label{
			display: inline-block;
   			 width: 80px;
		}
		
		form input.auth {
			display: block;
		}
		
		
		.btn{
		
		
		    border-color: black;
		
		}

	</style>

<section>
	<form:form modelAttribute="userVO">
	<div>
		<label for="username">UserName</label>
		<form:input path="username" readonly="true"/>
	</div>
	<div>
		<label for="email">email</label>
		<form:input path="email"/>
	</div>
	<div>
		<label for="phone">phone</label>
		<form:input path="phone" />
	</div>
	<div>
		<label for="address">address</label>
		<form:input path="address" />
	</div>
	<div>
		<label for="address" style="color:red;">계정활성화</label>
		<form:checkbox path="enabled"/>
	</div>

	<div id="auth_box">
	<button class="btn" id="auth_append" type="button">권한 정보 입력 추가</button>
	<c:if test="${not empty userVO.authorities}">
		<c:forEach items="${userVO.authorities}" var="auth">
			<input name="auth" 
					value="${auth.authority}" 
					class="auth">
		</c:forEach>
	</c:if>
	</div>
	<div>
		<button class="btn" id="btn_save" type="button">저장</button>
	</div>
	</form:form>
</section>

