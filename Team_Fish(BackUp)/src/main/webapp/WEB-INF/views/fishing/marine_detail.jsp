<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:set var="rootPath" value="${pageContext.request.contextPath}"/>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>





<style>


.marineListBoxDiv{
	border: 1px solid red;
    width: 90%;
    margin: 0 auto;
    flex-wrap: wrap;
    display: flex;
    padding: 0;
   
}

.listContent{
	border: 1px solid black;
	width:150px;
	height:150px;
	margin: 0 auto;
	display: none;
}

.listContentTitle{
	border: 1px solid black;
	width:150px;
	height:150px;
	margin: 0 auto;
	font-weight: bold;
	display: none;
}

.listThumbnail{
	width:150px;
	height:150px;
}

.img-thumbnail{
	border:none;
}

.allCoverBoxDiv{
	border:1px solid green;
	display: flex;
}

.prevBtn{
	display:flex;
}

.nextBtn{
	display:flex;
}

.inputCollectionDiv{
	text-align: center;
	margin: 10px 0;
}

.inputClass{
	width: 1%;
	margin: 0 10px;
}

.nextBtnA{
	align-self: center;
}

.prevBtnA{
	align-self: center;
}

</style>


    
<script>
$(function(){
	
	
	var marineList
	
	

	// ajax로 List 불러오기
	
	
	//Math.floor(): 소수점 버림
	//parseFloat
	// 파싱안됨 NaN뜸
	var mapX = $('#mapX').text()
	var mapY = $('#mapY').text()
	

	var xIndex = mapX.indexOf('.')
	var resultX = mapX.substring(0,xIndex).replace(" ", "")

	
	var YIndex = mapY.indexOf('.')
	var resultY = mapY.substring(0,YIndex).replace(" ", "")
	
	
	var dataPerPage = 10 // 한 페이지에 나타낼 데이터 수
	var pageCount = 5 // 한 화면에 나타낼 페이지 수
	var totalPage// 총 페이지 수
	var curPage = 1
	

		
	
	
		
		var lengthVal
		
		
			$.ajax({
				
				url:"${rootPath}/marinelifeapi/getXYmarine", data:{mapX:33, mapY:124}, type:'get',
				success:function(result){
					// 리스트 넘어옴
					
					
					marineList = result
					
					
					
					
					$.each(marineList,function(i){
					
						
						//alert(i)
						
						
						// 제목만 제공하는 생물
						if(marineList[i].fullInfo == false){
							
							$('.marineListBoxDiv').append("<div id='id" + i + "'  class='listContentTitle'>" + marineList[i].title + "<div class='descriptionAtTitle' value='" + marineList[i].description + "'></div></div>")
							
						// 모든 정보 제공하는 생물 중에서		
						}else{
						
							// 섬네일이 있는 생물(정보 제공 완전체)
							if(marineList[i].thumbnail.length > 0 ){
							$('.marineListBoxDiv').append("<div id='id" + i + "' class='listContent'><img class='img-thumbnail listThumbnail' src='"  +  marineList[i].thumbnail  +"'/><div class='title' value='"+ marineList[i].title + "'></div><div class='description' value='"+ marineList[i].description + "'></div></div>")
							}
							// 섬네일이 없는 생물
							else{
								$('.marineListBoxDiv').append("<div id='id" + i + "' class='listContentTitle'>" + marineList[i].title + "<div class='descriptionAtTitle' value='" + marineList[i].description + "'></div></div>")
							}
						
						}
						
						
						
						if(i < 10){
							var id = 'id'+i
							$('#'+id).css('display','block')
						}
						
						
					})
					
					
					
					
					$('#marineListLength').val(marineList.length)
					
					var totalPage = Math.ceil(marineList.length/dataPerPage)
					
					$('#marineListTotalPage').val(totalPage)
					$('#currentPage').val(curPage)
					
					
					
				},error:function(){
					alert('서버 에러')
				}
				
			})


	// 다음 버튼 클릭됐을 때
	$('.nextBtn').click(function(){
			
	
		// 현재 페이지값이 0이상인가 그리고 totalPage 이하의 값인가
		if( parseInt($('#currentPage').val()) > 0 && parseInt($('#currentPage').val()) < parseInt($('#marineListTotalPage').val()) ){
			
			// 맞으면 currPage +1, 리스트 all display none, 해당 index의 리스트값만 display block
			
			$('.listContent').css('display','none')
			$('.listContentTitle').css('display','none')
			$('#currentPage').val( parseInt($('#currentPage').val()) + parseInt(1) )
			
			
			
			// 10개의 리스트 새로 display block
			var startIndex = (parseInt($('#currentPage').val()) - parseInt(1)) * 10 
			
			
			for(var i = 0; i < 10; i++){
				
				// 
				if( $( '#id'+(parseInt(startIndex)+parseInt(i))) ){
					$( '#id'+(parseInt(startIndex)+parseInt(i)) ).css('display', 'block')
				}else{
					break
				}
			}
			
		}
		
	})
	
	
	
	$('.prevBtn').click(function(){
			
	
		// 현재 페이지값이 2이상인가
		if(parseInt($('#currentPage').val()) > 1){
			
			// 맞으면 currPage -1, 리스트 all display none, 해당 index의 리스트값만 display block
			
			$('.listContent').css('display','none')
			$('.listContentTitle').css('display','none')
			$('#currentPage').val( parseInt($('#currentPage').val()) - parseInt(1) )
			
		
			
			// 10개의 리스트 새로 display block
			var startIndex = (parseInt($('#currentPage').val()) - parseInt(1)) * 10 
			
			
			for(var i = 0; i < 10; i++){
				
				
				if( $( '#id'+(parseInt(startIndex)+parseInt(i))) ){
					$( '#id'+(parseInt(startIndex)+parseInt(i)) ).css('display', 'block')
				}else{
					break
				}
			}
			
		}
		
	})
	
	
	
	
	
	
	
	

	

	
	
	
})
</script>




<div class="allCoverBoxDiv">

<div class="prevBtn">

<div class="prevBtnA">뒤로</div>

</div>

<div class="marineListBoxDiv">


</div>


<div class="nextBtn">

<div class="nextBtnA">앞으로</div>

</div>




</div>

<div class="inputCollectionDiv">
<input id="marineListLength" type="hidden">
<input class="inputClass" id="currentPage" readonly="readonly">/
<input class="inputClass" id="marineListTotalPage" readonly="readonly">
</div>