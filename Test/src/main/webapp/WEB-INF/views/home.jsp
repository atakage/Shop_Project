<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>
<head>
	<title>Home</title>
</head>




<script>
$(function(){
	


	
	$.startAjax = function(){
		
		
		var lengthVal = 5
		
	
			return lengthVal
			
	}
	
	
	var lengthResult = $.startAjax()
	
	
	alert(lengthResult)




})

</script>

<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
