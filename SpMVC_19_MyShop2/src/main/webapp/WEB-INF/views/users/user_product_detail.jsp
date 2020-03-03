<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Heroic Features - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<link href="${rootPath}/js/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${rootPath}/js/css/heroic-features.css" rel="stylesheet">

</head>
<style>
.detail-box {
	display: flex;
}

.row {
	margin-left: 50px;
}

.row {
	margin: 20px;
}

.key {
	font-size: 20px;
}

.context {
	font-size: 15px;
}
</style>
<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Start Bootstrap</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active"><a class="nav-link" href="#">로그인</a></li>
					<li class="nav-item active"><a class="nav-link" href="#">로그아웃</a></li>
					<li class="nav-item"><input type="search"></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="detail-box">
			<img src="http://placehold.it/500x325" alt="">
			<div class="content-box container">
				<div class="row">
					<div class="key col-6">상품이름</div>
					<div class="context col-6">${B2C_DTO.p_name}</div>
				</div>
				<div class="row">
					<div class="col-6">판매가격</div>
					<div class="context col-6">${B2C_DTO.p_iprice}</div>
				</div>
				<div class="row">
					<div class="col-6">설명</div>
					<div class="context col-6">${B2C_DTO.p_detail}</div>
				</div>
				<button>담아만 놔</button>
			</div>
		</div>
		<hr>
	</div>


	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2019</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="${rootPath}/js/vendor/jquery/jquery.min.js"></script>
	<script
		src="${rootPath}/js/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
