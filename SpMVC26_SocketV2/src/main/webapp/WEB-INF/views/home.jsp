<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 JSP 홈페이지</title>
<style>


#message_list{

	border : 1px solid red;
	height: 25vh;
	overflow: auto;

}

.userName {
	color: blue;
	font-weight: bold;
}

.from, .to {
	padding: 5px;
}
</style>
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>



<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>





<script>
	var socket = new SockJS('http://localhost:8080/socket/chat');
	var socket = new SockJS('http://192.168.4.12:8080/socket/chat');

	// 소켓 서버에 접속
	// 소켓이 서버에 접속한 다음 최초에 실행될 코드
	socket.onopen = function() {

		let userName = prompt("채팅방 입장!! 이름을 입력핫헤요")

		if (userName && userName != "") {
			socket.send("userName:" + userName)
		}

		socket.send('getUserList:' + userName)

	};

	// 서버로부터 수신되는 이벤트
	socket.onmessage = function(e) {
		console.log('message', e.data);
		//alert(e.data)

		//문자열형으로 수신된 json문자열을 json객체로 변환
		let messageJson = JSON.parse(e.data)

		// 서버로부터 전달받은 데이터에 msg라는 key가 있나, userName인가?
		if (messageJson.msg && messageJson.msg == 'userName') {
			$('#userName').val(messageJson.userName)
			$('#userName').prop('readonly', true)
			return false;
		}

		if (messageJson.msg && messageJson.msg == 'userList') {

			let userList = JSON.parse(messageJson.userList)

			// js코드 내장 객체 method
			// userList객체 그룹 중에서 각 요소의 키 값만 추출하여 배열로 만듦
			let userListKeys = Object.keys(userList)

			$('#toList').empty()
			// 동적 tag만드는 jquery 코드
			$('#toList').append($("<option/>", {
				value : "all",
				text : "전체"
			}))

			for (let i = 0; i < userListKeys.length; i++) {
				console.log("사용이름sessionid: ",
						userList[userListKeys[i]].userName)

				// 동적 tag만드는 jquery 코드
				$('#toList').append($("<option/>", {
					value : userListKeys[i],
					text : userList[userListKeys[i]].userName
				}))

			}

			return false;

		}

		let html = "<div class='from text-right'>"
		html += "<span class='userName'>" + messageJson.userName + "</span>"
				+ ":"
		html += messageJson.message
		html += "</div>"

		$("#message_list").append(html)
		$('#message_list').scrollTop($('#message_list').prop('scrollHeight'))
		//sock.close();
	};

	// 소켓 서버와 접속 종료
	/*
	socket.onclose = function() {
		console.log('close');
	};
	 */
</script>
<script>
	$(function() {

		$(document).on('submit', 'form', function(event) {

			event.preventDefault()
			//let message = $('#message').val()
			//$('#message_list').append("나>> " + message + "<br/>")

			//socket.send(message)

		})

		$(document).on('click', '#btn_send', function() {

			let toUserId = $('#toList').val()

			alert("유저아이디" + toUserId)

			let toUserName = $('#toList option:checked').text()

			let userName = $('#userName').val()
			if (userName == "") {
				alert('보내는 사람 이름을 입력')
				return false
			}

			// userName과 message를 묶어서 JSON 데이터로 생성
			let message = {
				userName : userName,
				toUser : toUserId,
				message : $('#message').val()
			}

			let html = "<div class='to'>"
			html += "<span class='userName'>"
			html += "나:"
			html += "</span>"
			html += message.message
			html += "<span>("
			html += toUserName
			html += ")</span>"
			html += "</div>"

			$('#message_list').append(html)
			$('#message_list').scrollTop($('#message_list').prop('scrollHeight'))
			// socket을 통해 json 데이터를 보내기 위해 json형 문자열로 벼변환 후 전송
			socket.send(JSON.stringify(message))
			$('#message').val('')

		})

	})
</script>
</head>
<body>
	<header class="jumbotron bg-success">
		<h2 class="text-white text-center">MY CHAT SERVICE</h2>
	</header>
	<section class="container-fluid">
		
		<div id="message_list"></div>
	</section>
	<section class="container-fluid">
		<form>
			<div class="form-group">
				<label for="userName">FROM</label> <input id="userName"
					class="form-control" placeholder="보내는 사람"><br />
			</div>

			<div class="form-group">
				<label for="toList">받는 사람</label> <select id="toList"
					class="form-control">
					<option value="all">전체</option>
				</select>
			</div>
			<div class="form-group">
				<label for="message">메시지</label> <input id="message"
					class="form-control" placeholder="메시지 입력">
			</div>
			<button id="btn_send" class="btn btn-primary">전송</button>
		</form>
	</section>

</body>
</html>