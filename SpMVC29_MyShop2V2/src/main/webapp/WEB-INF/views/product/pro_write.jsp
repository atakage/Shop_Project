<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>
    <c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



<meta charset="UTF-8">

</head>


<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<script>
$(function(){
		
	
	// ajax를 호출하여 가져온 form에서는 사용하면 안 되는 이벤트 , $().click(function(){}) 사용
	$(document).on('click','button.save',function(){
		
		let p_code = $('#p_code').val()
		if(p_code == ''){
			$('div#p_code_error').html('<b>*상품코드는 반드시 입력하세요<b>')
			$('#p_code').focus()
			return false
		}
		
		$('form').submit()
		
		
	})
		
		$(document).on('blur','#p_code',function(){
		
		// 상품 코드는 중복되면 안 되기 때문에
		// 중복된 상품코드를 미리 검사해 봄
		
		let p_code = $('#p_code').val()
		if(p_code == ''){
			$('div#p_code_error').html('<b>*상품코드는 반드시 입력하세요<b>')
			$('#p_code').focus()
			return false
		}
		
		$.ajax({
			url:"${rootPath}/product/code_check", data:{p_code:p_code}, type:"get",
			success:function(result){
				if(result == 'EXISTS'){
					$('div#p_code_error').html('이미 사용 중인 코드입니다')
					$('#p_code').focus()
					return false
				}else if(result == 'NONE'){
					$('div#p_code_error').html("<font color='blue'>사용가능한 코드입니다</font>")
					
					/*
						상품코드 중복 검사를 ajax로 실행한 후 데이터를 서버로 submit할 때는 
						ajax가 끝난 다음 success 함수 내에서 실행을 해 주어야 함
						만약 ajax 코드밖에서 submit을 수행하면
						ajax로 중복검사를 수행하기 전에 submit이 실행돼 버릴 수 있음
					*/
					
				}
				
			},error:function(x, error){
				$('div#p_code_error').html('서버 통신 오류')
			}
		})
		
		
		
		
		
	})
})
</script>



<section class="container body">
	<form:form modelAttribute="productVO" enctype="multipart/form-data" action="?${_csrf.parameterName}=${_csrf.token}">
		<fieldset>
			<legend>상품정보 등록</legend>
			<div id="p_code_error" class="text-danger"></div>
			<div class="form-group">
				<form:input class="form-control" path="p_code" placeholder="상품코드"/>
			</div>
			
			<div class="form-group">
				<form:input class="form-control" path="p_name" placeholder="상품이름"/>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="p_oprice" placeholder="판매가격"/>
			</div>
			<div class="form-group">
				<input type="file" id="file" name="file">
			</div>
			<div class="button-group text-right">
				<button class="btn btn-primary save" type="button">저장</button>
				<button class="btn btn-success" type="button">상품목록으로</button>
			</div>
			
		</fieldset>
	</form:form>
</section>



</html>