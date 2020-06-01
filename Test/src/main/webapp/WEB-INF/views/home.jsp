<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>

<script>

$(function(){
	
	$('.btn').click(function(){
		
		
		
		$.ajax({
			url:'${rootPath}/callCmt', type:'get', data:{text:$('.text').text()},
			success:function(result){
				$('.cmtIn').html(result)
			}
		})
		
	})
	
})


</script>


<head>
	<title>Home</title>
</head>



<body>
<h1>
	Hello world!  
</h1>


<div class="text">bbb</div>

<button type="button" class="btn">btn</button>


<div class="cmtIn">

</div>
</body>
</html>
