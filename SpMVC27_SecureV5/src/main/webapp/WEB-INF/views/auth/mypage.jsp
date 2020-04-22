<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>



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




<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
<script>
	$(function() {
		$("input").prop("readonly", true)

		$(document).on("click", "#btn_update", function() {
			let pass = $("#password").val()
			if (pass == "") {
				alert("수정하려면 비밀번호를 입력후 \n" + "다시 수정버튼을 클릭하세요")
				$("div.password").css("display", "block")
				$("#password").prop("readonly", false)
				$("#password").focus()
				return false;
			}
			if (pass != "") {

				$.ajax({
					url : '${rootPath}/user/password',
					method : 'POST',
					data : {
						password : pass,
						"${_csrf.parameterName}" : "${_csrf.token}"
					},
					success : function(result) {
						if (result == "PASS_OK") {

							$("input").prop("readonly", false)
							$("input").css("color", "blue")
							$("button#btn_save").prop("disabled", false)
							$("button#btn_update").prop("disabled", true)

						} else {
							alert("비밀번호가 일치하지 않습니다")
						}
					},
					error : function() {
						alert('서버 통신 오류')
					}
				})
			}
		})

	})
</script>
<style>
section {
	position: fixed;
	top: 30%;
	left: 0;
	width: 100%;
}

form {

	box-shadow: 1px 1px;
	width: 50%;
	text-align: center;
	margin: 0 auto;
	border: 2px solid deepskyblue;
}

form div.password {
	display: none;
}

form input.auth {
	display: block;
}

input {
	border-color: deepskyblue;
	font-weight: bold;
}

.btn{
	border: 1px solid deepskyblue;
	color: deepskyblue;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>
	<section>



		<form:form modelAttribute="userVO">
			<div
				style="font-size: xx-large; font-weight: bold; color: deepskyblue;">MY
				PAGE</div>
			<div class="form-group">
				<form:input path="username" />
			</div>
			<div class="form-group password">
				<input id="password" type="password" placeholder="비밀번호를 입력!!">
			</div>
			<div class="form-group">
				<form:input path="email" placeholder="E-mail" />
			</div>
			<div class="form-group">
				<form:input path="phone" placeholder="전화번호" />
			</div>
			<div class="form-group">
				<form:input path="address" placeholder="주소" />
			</div>

			<div style="padding-bottom: 20px;">
				<button class="btn" type="button" id="btn_update">수정</button>
				<button class="btn" type="submit" id="btn_save" disabled="disabled">저장</button>
				<button class="btn" type="button" id="btn_loss_pass">비밀번호찾기</button>
			</div>
		</form:form>
	</section>


</body>
</html>





