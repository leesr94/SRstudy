<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>list.jsp</h1>
	<h4>http://localhost:8088/member/list</h4>
	
	<h1>회원목록</h1>
	
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>회원가입일</th>
		</tr>
		
	<c:forEach var="vo" items="${memberList}">
		<tr>
			<td>${vo.userid}</td>
			<td>${vo.userpw}</td>
			<td>${vo.username}</td>
			<td>${vo.useremail}</td>
			<td>${vo.regdate}</td>
		</tr>
	</c:forEach>
	</table>
	
</body>
</html>