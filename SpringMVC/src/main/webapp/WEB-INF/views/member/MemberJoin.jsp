<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>MemberJoin.jsp</h1>
	<h3>http://localhost:8088/member/join</h3>
	
<!-- 	<form action="./MemberJoinAction.me" method="post"> -->
	<fieldset>
		<form action="" method="post">
		<!-- action 값을 주지 않으면 자기 자신의 페이지를 호출 -> 방식차이만 있을 뿐 현재는 join이라는 주소가 동일하므로 비우기 가능 -->
			아이디 : <input type="text" name="userid"> <br>
			비밀번호 : <input type="password" name="userpw"> <br>
			이름 : <input type="text" name="username"> <br>
			이메일 : <input type="text" name="useremail"> <br>
			<hr>
			<input type="submit" value="회원가입">
		</form>
	</fieldset>
	
</body>
</html>