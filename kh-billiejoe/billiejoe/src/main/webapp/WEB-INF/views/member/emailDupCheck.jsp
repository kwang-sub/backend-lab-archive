<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 중복 검사</title>
</head>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<body>
	<form action="${contextPath }/member/emailDupCheck" id="emailCheckForm" method="post">
		<input type="text" id="email" name="email" >
		<input type="submit" value="중복확인">
	</form>
	<br>
</body>
</html>