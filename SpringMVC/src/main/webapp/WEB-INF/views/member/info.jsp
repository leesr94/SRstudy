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
	
	<h1>info.jsp</h1>
	<h4>http://localhost:8088/member/info</h4>
	
	<!-- 세션 제어 -->
	<c:if test="${empty id}">
		<c:redirect url="/member/login"/>
	</c:if>
	
	
	<!-- 정보 페이지 -->
	<table border="1">
		<tr>
			<th>아이디</th>
<%-- 			<td>${resultVO.userid}</td> --%>
			<td>${memberVO.userid}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
<%-- 			<td>${resultVO.userpw}</td> --%>
			<td>${memberVO.userpw}</td>
		</tr>
		<tr>
			<th>이름</th>
<%-- 			<td>${resultVO.username}</td> --%>
			<td>${memberVO.username}</td>
		</tr>
		<tr>
			<th>이메일</th>
<%-- 			<td>${resultVO.useremail}</td> --%>
			<td>${memberVO.useremail}</td>
		</tr>
		<tr>
			<th>회원가입일</th>
<%-- 			<td>${resultVO.regdate}</td> --%>
			<td>${memberVO.regdate}</td>
		</tr>
	</table>
	
	<a href="/member/main">메인 페이지로</a>
	
</body>
</html>