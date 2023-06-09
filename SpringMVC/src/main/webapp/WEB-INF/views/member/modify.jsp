<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>modify.jsp</h1>
	<h4>http://localhost:8088/member/modify</h4>
	
	<form method="post">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userid" value="${resultVO.userid}" readonly></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="userpw" placeholder="비밀번호를 입력하세요"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="username" value="${resultVO.username}"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="useremail" value="${resultVO.useremail}"></td>
			</tr>
		</table>
		<input type="submit" value="수정하기">
	</form>
	
</body>
</html>