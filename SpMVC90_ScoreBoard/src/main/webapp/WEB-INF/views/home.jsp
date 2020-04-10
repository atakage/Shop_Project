<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 JSP 홈페이지</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



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


<style>


.modal{


background-color: rgba(240, 248, 255, 0.5)


}


.modal-content{


margin-top: 40%;

}


</style>

<script>
	$(function() {

		$(document).on('click', '#createStudent', function() {

			$('#myModal').css('display', 'block')

		})
		
		$(document).on('click', '.close', function() {

			$('#myModal').css('display', 'none')
			$('#s_myModal').css('display', 'none')

		})
		
		$(document).on('click', '.btnClose', function() {

			$('#myModal').css('display', 'none')
			$('#s_myModal').css('display', 'none')
		})
		
		
		
		$(document).on('click', '.btnStudentInsert', function(){
			
			
			var student = $('form').serialize()
			
			alert(student)
		
			
			$.ajax({
				
				url:'${rootPath}/student/insert', data:student, type:'post',
				success:function(result){
					
					alert(result)
					document.location.href = "${rootPath}/"
					
				},error:function(){
					alert('서버 에러')
				}
				
				
			})
			
			
		})
		
		
		
		
		$(document).on('click','#createScore', function(){
			
			
			$('#s_myModal').css('display', 'block')
			
			
		})
		
		
		
		$(document).on('blur', '#s_num', function(){
			
			
			var s_num = $('#s_num').val()
			
			$.ajax({
				
				
				url:'${rootPath}/student/findstname', data:{s_num:s_num}, type:'post',
				success:function(result){
					
					
					if(result == 'null'){
						alert('존재하지 않는 학번입니다')
						$('#s_name').removeAttr('value')
						return false;
					}
					
					$('#s_name').attr('value',result)
					
				}, error:function(){
					alert('서버 에러')
				}
				
				
			})
			
		})
		
		
		
		
		$(document).on('click','.btnScoreInsert', function(){
			
			
			var score = $('form').serialize()
			
			alert(score)
			
			
			
			$.ajax({
				
				
				url:'${rootPath}/student/insertscore', data:score, type:'post',
				success:function(result){
					
					if(result == 'ok'){
						alert('등록 성공')
						document.location.href = "${rootPath}/"
						
					}else{
					
					alert('등록 실패')
					return false;
					
					}
				
				}, error:function(){
					alert('서버 에러')
				}
				
				
			})
			
			
		})
		
		
		

	})
</script>

<body>


	<button id="createStudent" type="button" data-toggle="modal"
		data-target="">학생 명부 등록</button>
		<button id="createScore" type="button" data-toggle="modal"
		data-target="">학생 성적 등록</button>


	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">



				<div class="modal-header">
					<h4 class="modal-title">학생 명부 등록</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>


				<div class="modal-body">
				
					<form:form action="" method="post">
							<div>
							<input style="width:50%" id="st_name"   name="st_name" placeholder="이름"><a style="font-size: x-small;">*세 글자 이내</a>
							</div>
							<div>
							<input style="width:50%" id="st_class" name="st_class" placeholder="학년"><a style="font-size: x-small;">학년</a>
							</div>
							<div>
							<input style="width:50%" id="st_grouptbl_student" name="st_grouptbl_student" placeholder="반"><a style="font-size: x-small;">반</a>
							</div>
					</form:form>
					
				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-primary btnStudentInsert">등록</button>
					<button type="button" class="btn btn-danger btnClose" data-dismiss="modal">닫기</button>
				</div>



			</div>
		</div>
	</div>
	
	
	
	
	
	<div class="modal" id="s_myModal">
		<div class="modal-dialog">
			<div class="modal-content">



				<div class="modal-header">
					<h4 class="modal-title">학생 성적 등록</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>


				<div class="modal-body">
				
					<form:form action="" method="post">
							<div>
							<input style="width:50%" id="s_num"   name="s_num" placeholder="학번">
							</div>
							<div>
							<input style="width:50%" id="s_name"   placeholder="이름" readonly="readonly"><a style="font-size: x-small;">학번 입력 시 자동 입력</a>
							</div>
							<div>
							<input style="width:50%" id="s_korean" name="s_korean" placeholder="국어"><a style="font-size: x-small;">점</a>
							</div>
							<div>
							<input style="width:50%" id="s_english" name="s_english" placeholder="영어"><a style="font-size: x-small;">점</a>
							</div>
							<div>
							<input style="width:50%" id="s_math" name="s_math" placeholder="수학"><a style="font-size: x-small;">점</a>
							</div>
					</form:form>
					
				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-primary btnScoreInsert">등록</button>
					<button type="button" class="btn btn-danger btnClose" data-dismiss="modal">닫기</button>
				</div>



			</div>
		</div>
	</div>








<div>






<table border="1" style="text-align: center;">
	<tr>
		<th>학번</th>
		<th>이름</th>
		<th>학년</th>
		<th>반</th>
		<th>국어</th>
		<th>영어</th>
		<th>수학</th>
		<th>총점</th>
		<th>평균</th>
		<th>석차</th>
	</tr>
	
	
	<c:choose>
	
	
		<c:when test="${! list }">
		
			
			<tr>
				<td colspan="10">데이터 없음</td>
			</tr>
		
		</c:when>
		<c:otherwise>
		
			<c:forEach items="${list}" var="list">
		
			<tr>	
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		
			</c:forEach>
		</c:otherwise>
	
	
	</c:choose>
	

</table>







</div>



</body>
</html>