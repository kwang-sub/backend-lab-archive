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
<title>회원 탈퇴</title>

	<link href="${contextPath}/resources/css/memberStyle.css" rel="stylesheet">

	<style>
		input::-webkit-input-placeholder {
			text-align: center;
		}
		
		input{
			width:500px;
		}
	</style>

</head>
<body>
	<!-- // header.jsp -->
	<jsp:include page="../common/header.jsp"></jsp:include>

	<!-- 본문 시작 -->
	<!-- 각 페이지의 jsp 파일은 header와 footer를 제외한 본문만 남겨서 작성해주세요. (이클립스에서 보여주는 빨간줄 표시는 무시하셔도 됩니다.) -->
	<!-- !!! 강사님은 알아서 합쳐주니 괜찮다고 하셨지만 세 부분으로 나누어 졌을 때 겹치는 곳이 없고 셋이 합쳐 하나의 완성된 html 파일이 되도록 include 되어야 합니다 !!! -->
	<div class="container py-5" align="center">
		<div>
			<form action="secession" method="post" id="secession" class="form-signin" >
				<div class="row text-center">
					<h2 style="font-weight: 600;">회원 탈퇴</h2>
				</div>
	
				<br>
				<br>
	
				<div class="row text-center">
					<p>진행 중인 예약 사항이 있을 경우 회원 탈퇴는 불가합니다.</p>
					<p>탈퇴를 위해 진행 중인 예약은 모두 취소하세요.</p>
				</div>
	
				<br>
	
				<div class="row text-center">
					<p>탈퇴를 위한 현재 비밀번호를 입력하세요.</p>
				</div>
	
				<div class="row sec">
					<input class="form-control sec" type="password" id="memberPw" name="memberPw" placeholder="현재 비밀번호를 입력하세요">
				</div>
	
				<br>
	
				<div class="row text-center">
					<p>정말로 탈퇴하시겠습니까?</p>
				</div>
				
			</form>
				<div class="row sec">
					<button type="submit" class="btn btn-primary" onclick="return secession();">회원 탈퇴</button>
				</div>
			
		</div>
	</div>
	<!-- // 본문 끝 -->

	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script src="${contextPath}/resources/js/member.js"></script>
</body>
</html>