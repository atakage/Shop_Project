<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <c:set var="rootPath" value="${pageContext.request.contextPath}"/>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  
    <script>
	$(function(){
		
		
		
		
		
		
		$('.deleteButton').click(function(){
			
			var urlParams = new URLSearchParams(window.location.search);
			var b_id =  urlParams.get('b_id')
			
			alert(b_id)
			
				if(b_id == null){
					
					alert('삭제할 수 없습니다')
					return false;
					
				}else{
					
					
					if(confirm('정말 삭제?')){
					
						document.location.href="${rootPath}/delete?b_id="+ b_id
					}
					
					
					
				}
			
			
			
		})
		
		
		
		$('.updateButton').click(function(){
		
			
			
			var urlParams = new URLSearchParams(window.location.search)
			var b_id = urlParams.get('b_id')
			
			alert(b_id)
			
			
			document.location.href="${rootPath}/update?b_id=" + b_id
			
		})
		
		
		
		$('.commentButton').click(function(){
			
			
			var c_writer = $('.c_writer').val()
			var c_subject = $('.c_subject').val()
			
			var urlParams = new URLSearchParams(window.location.search)
			var c_b_id = urlParams.get('b_id')
			
			//  var formData  =  $('form').serialize()  form에 있는 데이터를 전부 묶어줌
			
			/*
			
			
			$.ajax({
				
				url: "${rootPath}/comment/insert", data:formData, type:'POST',
				success:function(result){
					$('div.cmt-list').html(result)
				}, error:function(){
					alert('서버 오류')
				}
				
			})
			
			
			
			
			// let c_id = $(this).parent('div').data('id')   , 현재 자신을 감싸고 있는 부모 div를 찾음
			// event.stopPropagation() 나를 감싸고 있는 곳으로 이벤트가 전파되는 것을 막음
			
			
			
			*/
			
			
			
			$.ajax({
				
				
				
				url:"${rootPath}/comment/insert", data:{c_writer:c_writer, c_subject:c_subject, c_b_id:c_b_id}, type:"POST",
				success:function(result){
					
					
						alert('등록성공')
						//document.location.href = "${rootPath}/detail?b_id="+c_b_id
								
						$('div.cmt-list').html(result)
					
					
					
				}, error:function(){
					
					alert('서버 오류')
					
				}
				
				
			})
			
			
			
			
			
			
			
			
			
		})
		
		
		
		$('.commentDeleteButton').click(function(){
			
			
			
			
			$.ajax({
				
				
				url:"${rootPath}/comment/delete", data:${COMMENT.c_id}, type:"POST",
				success:function(result){
					alert('삭제 성공')
					$('div.cmt-list').html(result)
				}
				
				
			})
			
			
		})
		
		
		
		
	})
	
	
	</script>
    
 <head>
    
      <%@ include file ="/WEB-INF/views/include/include-head.jspf" %>
    
  </head>

<body>
	
	<%@ include file ="/WEB-INF/views/include/include-header.jspf" %>   

	<section class="container-fluid">
	
		<h2 class="p-3">${BBS.b_subject}</h2>
		<hr/>
		<div class="row">
		
			
			<small class="col-5">${BBS.b_writer}</small>
			<small class="col-5">${BBS.b_date_time}</small>
		
		</div>
		<hr/>
		
		<div>
			${BBS.b_content}
		</div>
	
	</section>
	<section class="container-fluid bg-light p-4">
	
	<div class="cmt-list">
	<c:if test="${!empty COMMENT}">
	
	
		<%@include file="/WEB-INF/views/comment_list.jsp" %>
		
		
	</c:if>	
	</div>
	
		<div class="form-group" style="margin-top: 3%; ">
			<input class="form-control c_writer" placeholder="작성자"/>
			<input class="form-control c_subject" placeholder="댓글"/>
			<button class="btn btn-Secondary mr-2 commentButton" type="button">등록</button>
			
		</div>
	</section>


	<div class="form-group d-flex justify-content-end">
	<button class="btn btn-info mr-2 updateButton" type="button">수정</button>
	<button class="btn btn-danger mr-2 deleteButton" type="button">삭제</button>
	<button class="btn btn-success" type="button" onclick="document.location.href='${rootPath}/list'">목록</button>
	</div>


	
</body>