<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath}" />

<!-- header.jsp -->
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>

	<link href="${contextPath}/resources/css/memberStyle.css" rel="stylesheet">

</head>

<body>
	<!-- // header.jsp -->
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- 본문 시작 -->
	<!-- 각 페이지의 jsp 파일은 header와 footer를 제외한 본문만 남겨서 작성해주세요. (이클립스에서 보여주는 빨간줄 표시는 무시하셔도 됩니다.) -->
	<!-- !!! 강사님은 알아서 합쳐주니 괜찮다고 하셨지만 세 부분으로 나누어 졌을 때 겹치는 곳이 없고 셋이 합쳐 하나의 완성된 html 파일이 되도록 include 되어야 합니다 !!! -->
	<div class="container py-5 outer">
		<div class="container outer">
			<!-- 회원가입 form태그 -->
			<form action="signUp" method="POST"  enctype="multipart/form-data" class="needs-validation" name="signUpForm" onsubmit="return validate();">
				
				<div class="row text-center signRow">
					<h2>회원가입</h2>
				</div>
				<br>
				<br>
	
				<div class="row signRow">
					<div class="col-4 ">* ID(이메일)</div>
					<div class="col-8">
						<input class="form-control" type="email" id="email" name="memberEmail" placeholder="E-mail@billie-joe.co.kr" autocomplete="off" required>
						<div class="col-md-10 offset-md-3">
							<span id="checkEmail">&nbsp;</span>
						</div>
					</div>
				</div>
	
				<div class="row signRow">
					<div class="col-4 ">* 비밀번호</div>
					<div class="col-8">
						<input class="form-control" type="password" id="pwd1" name="memberPw" placeholder="영문/숫자/특수문자 혼합 8자리 이상" required>
						<div class="col-md-10 offset-md-3">
							<span id="checkPwd1">&nbsp;</span>
						</div>
					</div>
				</div>
	
				<div class="row signRow">
					<div class="col-4 ">* 비밀번호 확인</div>
					<div class="col-8">
						<input class="form-control" type="password" id="pwd2" placeholder="비밀번호를 다시 입력해주세요." required>
						<div class="col-md-10 offset-md-3">
							<span id="checkPwd2">&nbsp;</span>
						</div>
					</div>
				</div>
	
				<div class="row signRow">
					<div class="col-4 ">* 이름</div>
					<div class="col-8">
						<input class="form-control" type="text" id="inputName" name="memberName" placeholder="이름(10자 이하)" autocomplete="off" required>
						<div class="col-md-10 offset-md-3">
							<span id="checkName">&nbsp;</span>
						</div>
					</div>
				</div>
	
				<div class="row signRow">
					<div class="col-4 ">* 전화번호</div>
					<div class="col-8">
						<input class="form-control phone" type="text" id="phone" name="memberPhone" placeholder="휴대폰번호(10~11자리)" maxlength="13" autocomplete="off" required>
					</div>
				</div>
	
				<div class="row profileRow" >
					<div class="col pic">프로필 이미지(선택)</div>
					<div class="col-6">
						<div class="picture rounded-circle" id="pictureArea">
							<img class="img-thumbnail rounded-circle" id="memberPic">
						</div>
					</div>
				</div>
	
				<div id="fileArea">
					<input type="file" id="img" name="images" onchange="LoadImg(this,0)" accept="image/*">
				</div>
	
				<div class="row signRow">
					<button class="btn btn-primary btn-lg btn-block" type="submit">회원가입</button>
				</div>
			</form>
		</div>
	</div>

	<!-- // 본문 끝 -->

	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script src="${contextPath}/resources/js/member.js"></script>

</body>

</html>
<!-- //footer -->