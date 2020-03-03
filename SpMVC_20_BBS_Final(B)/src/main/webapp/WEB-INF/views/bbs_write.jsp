<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
    
    
    <!DOCTYPE html>
<html>
<head>
    
      <%@ include file ="/WEB-INF/views/include/include-head.jspf" %>
    
    
    
    <script>
	$(function(){
		
		
		
		
		var toolbar = [
			['style',['bold','italic','underline'] ],
			['fontsize',['fontsize']],
			['font Style',['fontname']],
			['color',['color']],
			['para',['ul','ol','paragraph']],
			['height',['height']],
			['table',['table']],
			['insert',['link','hr','picture']],
			['view',['fullscreen','codeview']]
			
		]
		
		$("#b_content").summernote({
			lang:'ko-KR',
			placeholder:'본문을 입력하세요',
			width:'100%',
			toolbar:toolbar,
			height:'200px',
			disableDragAndDrop : false, 
			callbacks : {
				
				onImageUpload : function(files, editor, isEdite){
					
					for(let i = files.length - 1; i >=0; i--){
						
						// 파일을 1개씩 업로드할 함수
						upFile(files[i], this) // this = editor (summernote)
						
						
					}
					
				}
				
			}
		})// 
		
		// ajax 활용하여 파일을 서버로 업로드 수행하고 실제 저장된 파일이름을 받아서 
		// summernote에 기록된 내용 중 img src=""을 변경
		function upFile(file, editor){			// editor 담기는 곳?
			
			var formData = new FormData()
			
			// upFile 변수에 file정보를 담아 보내기 위한 준비
			// editor.insertImage 
			// summernote의 내장 함수를 callback 형태로 호출해서
			formData.append('upFile', file)
			
			
			$.ajax({
				
				url:"${rootPath}/image_up", type:"POST", data:formData, contentType: false, processData: false, enctype:"multipart/form-data",
				success:function(result){
					
					
					alert(result)
					result = "${rootPath}/files/" + result
					$(editor).summernote('editor.insertImage', result)
					
					
				}, error:function(){
					alert('서버 통신 오류')
				}
				
			})
			
		}
		
		
		
		
		$('#b_content').summernote()
		
		
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
		
		
	})
	
	
	</script>
    
    
  </head>
  
  
  

<body>
	
	<%@ include file ="/WEB-INF/views/include/include-header.jspf" %>
	
	
	

	<section class="container-fluid">
			
			<fieldset>
				<form method="post">
			
				<div class="form-group">
					<input class="form-control" name="b_writer" placeholder="작성자" value="${BBS.b_writer}">
				</div>
				<div class="form-group">
					<input class="form-control" name="b_subject" placeholder="제목" value="${BBS.b_subject}">
				</div>
				<div class="form-group">
					<textarea id="b_content" name="b_content" placeholder="내용" rows="10" cols="50">${BBS.b_content}</textarea>
				</div>
				<div class="form-group d-flex justify-content-end">
					<button class="btn btn-primary mr-2">저장</button>
					<button class="btn btn-danger mr-2 deleteButton" type="button">삭제</button>
					<button class="btn btn-success" type="button" onclick="document.location.href='${rootPath}/list'">목록</button>
				</div>
				
				</form>
			</fieldset>
			
	</section>

</body>    
</html>
    