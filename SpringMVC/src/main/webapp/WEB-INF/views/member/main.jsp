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
	
	<h1>main.jsp</h1>
	<h4>http://localhost:8088/member/main</h4>
	
	<!-- 로그인 정보가 없을 때 이동 (세션 제어) -->
<%-- 	<c:if test="${id == null}"> --%>
	<c:if test="${empty id}">
		<c:redirect url="/member/login" />
	</c:if>
	
	
	<!-- 메인 페이지 -->
	아이디 : ${sessionScope.id} <br>
	
	<input type="button" value="로그아웃" onclick="location.href='/member/logout';">
	
	<hr>
	
	<h3><a href="/member/info">회원정보 조회</a></h3>
	<h3><a href="/member/modify">회원정보 수정</a></h3>
	<h3><a href="/member/remove">회원정보 삭제</a></h3>
	
	<c:if test="${!empty id && id.equals('admin')}">
		<h3><a href="/member/list">회원목록</a></h3>
	</c:if>
	
</body>
</html>