<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 로그인</title>
  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
  <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	<link href="${contextPath}/resources/css/memberStyle.css" rel="stylesheet">
  
</head>
<body>

	<!-- 본문 시작 -->
	<!-- 각 페이지의 jsp 파일은 header와 footer를 제외한 본문만 남겨서 작성해주세요. (이클립스에서 보여주는 빨간줄 표시는 무시하셔도 됩니다.) -->
	<!-- !!! 강사님은 알아서 합쳐주니 괜찮다고 하셨지만 세 부분으로 나누어 졌을 때 겹치는 곳이 없고 셋이 합쳐 하나의 완성된 html 파일이 되도록 include 되어야 합니다 !!! -->
	<div class="container py-5 outer">
	
		<form action="login" method="post" class="form-signin" onsubmit="return loginValidate();">
			
			<div class="row text-center login">
				<h1>관리자 로그인</h1>
			</div>
	
			<div class="row login">
				<input class="form-control" type="text" id="memberEmail" name="memberEmail" placeholder="이메일을 입력하세요" value="${cookie.saveEmail.value }">
			</div>
	
			<div class="row login">
				<input class="form-control" type="password" id="memberPw" name="memberPw" placeholder="비밀번호를 입력하세요">
			</div>
	
			<c:if test="${ !empty cookie.saveEmail.value }">
				<c:set var="ch" value="checked" />
			</c:if>
	
			<div class="row text-center login">
				<label> <input type="checkbox" name="save" id="save" ${ch}>
					이메일 저장
				</label>
			</div>
	
			<div class="row login">
				<button type="submit" class="btn btn-warning">로그인</button>
			</div>
		</form>
	</div>

		<%-- 로그인 실패와 같은 메세지가 서버로부터 전달되어 온 경우 출력 --%>
		<c:if test="${!empty title}">
			<script>
				swal({
					"icon" : "${icon}",
					"title" : "${title}",
					"text" : "${text}"
				});
			</script>
		</c:if>

	<!-- // 본문 끝 -->

	<script src="${contextPath}/resources/js/member.js"></script>
</body>
</html>