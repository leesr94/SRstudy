<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>doC.jsp</h1>
	
	<hr>
	
	jsp 표현식(파라미터) : <%=request.getParameter("msg") %> <br>
	el 표현식(파라미터) : ${param.msg} <br>
	el 표현식 : ${msg} -> controller에서 modelatrribute 매개변수로 처리하여서 msg 만으로도 출력 가능 <br>
	
	<hr>
	
	el 표현식 : ${age} <br>
	
	<hr>
	
	vo : ${vo} <br>
	userid : ${userid} <br>
	userpw : ${userpw} <br>
	memberVO : ${memberVO} <br>
	tel : ${tel} <br>
	
</body>
</html>