<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>


		<c:forEach items="${COMMENT}" var="C">
		<div style="background-color: aliceblue;">
			<div><a>${C.c_writer}</a><a style="float: right;">${C.c_date_time}</a></div>
			<div style="margin-left: 1%;">${C.c_subject}</div>
			
			
		</div>
		<div style="display: flow-root; margin-bottom: 2%;">
		<button class="commentDeleteButton" style="font-size: small; float:right;">삭제</button><button class="commentUpdateButton" style="font-size: small; float:right;">수정</button>
		</div>
		
		</c:forEach>

