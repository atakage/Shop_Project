<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>


<section class="row justify-content-center container-fluid">
	<article class="col-md-7 col-sm-12 bg-light pro-input">
		<form:form action="${rootPath}/admin/dept/input"
			modelAttribute="deptVO">

			<div class="form-group">
				<form:input path="d_code" class="form-control" placeholder="" />
				<form:errors path="d_code" class="in-errors" />
			</div>
			
			<div class="form-group">
				<form:input path="d_name" class="form-control " placeholder="거래처이름" />
				<form:errors path="d_name" class="in-errors" />
			</div>
			
			<div class="form-group">
				<form:input path="d_ceo" class="form-control "
					placeholder="대표자명" />
				<form:errors path="d_ceo" class="in-errors" />
			</div>
			
			<div class="form-group">
				<form:input path="d_sid" class="form-control "
					placeholder="사업자 번호" />
				<form:errors path="d_sid" class="in-errors" />
			</div>
			
			<div class="form-group">
				<form:input path="d_tel" class="form-control "
					placeholder="대표전화" />
				<form:errors path="d_tel" class="in-errors" />
			</div>
			
			<div class="form-group">
				<form:input path="d_addr" class="form-control "
					placeholder="주소" />
				<form:errors path="d_addr" class="in-errors" />
			</div>
			
			<div class="form-group">
				<form:input path="d_manager" class="form-control "
					placeholder="담당자" />
				<form:errors path="d_manager" class="in-errors" />
			</div>
			
			<div class="form-group">
				<form:input path="d_mtel" class="form-control "
					placeholder="담당자 번호" />
				<form:errors path="d_mtel" class="in-errors" />
			</div>
			
			<div class="form-group">
				<form:input path="d_rem" class="form-control "
					placeholder="비고" />
				<form:errors path="d_rem" class="in-errors" />
			</div>
			
			<div class="form-group">
				<button>저장</button>
			</div>
		</form:form>
	</article>

	<article class="col-xl-4 col-sm-12 bg-light list-body">
		<%@ include file="/WEB-INF/views/admin/dept_list.jsp"%>
	</article>


</section>