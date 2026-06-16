<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
	
	

<style>
/* number 태그 화살표 제거 */
input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

.col-md-3 p {
	font-size: small;
}

#signUp-btn {
	background-color: #A66129;
	color: white;
	font-size: medium;
}

body {
	font-weight: bold;
	
	
}

.text-center{
height : 700px;
}

#idDupCheck, #nickDupCheck {
	background-color: gray;
	opacity: 0.7;
	color: black;
	font-weight: bold;
	margin-top : 3px;
}

#id {
	margin-bottom: 5px;
}

#email {
	margin-bottom: 5px;
}

p {
	font-size: 7px;
}

#logo-img {
	width: 150px;
	margin-left: 10%;
	margin-top: 15px;
}

#email1, #email2 {
	display: inline-block;
	width: 45%;
	float: left;
	margin-bottom: 0;
}

#at-sign {
	display: inline-block;
	box-sizing: border-box;
	float: left;
	width: 10%;
	text-align: center;
	font-size: 1.3em;
	font-weight: bold;
}

#email>p {
	display: inline-block;
	width: fit-content;
}

.form-row {
	margin-left: 2%;
}
</style>
</head>
<body>


	<div class="container">
		<!-- 
			상대 경로에서 사용하는 기호
			(빈칸) : 현재 폴더
			/	  : 하위 폴더
			..    : 상위 폴더
		 -->

		<div class="py-4 col-md-3 offset-md-3">
			<label for="id">회원가입</label>
		</div>




		<form method="POST" action="signUp" class="needs-validation"
			name="signUpForm" onsubmit="return validate();">

			<!-- 아이디 -->

			<div class="col-md-3 offset-md-3">
				<label for="id">아이디</label>
			</div>

			<div class="col-md-5 offset-md-3">
				<input type="text" class="form-control" name="id" id="id"
					maxlength="12" placeholder="아이디" autocomplete="off" required>
				<!-- required : 필수 입력 항목으로 지정 -->
				<!-- autocomplete="off" : input 태그 자동완성 기능을 끔 -->

				<!-- 팝업창 중복체크 여부 판단을 위한 hidden 타입 요소 -->
				<input type="hidden" name="idDup" id="idDup" value="false">
			</div>

			<!-- ajax 중복검사 시 필요없음 -->
			<div class="col-md-5 offset-md-3">
				<button type="button" class="btn btn-sm btn-block btn-light"
					id="idDupCheck">아이디 중복검사</button>
			</div>



			<div class="col-md-6 offset-md-3">
				<span id="checkId">&nbsp;</span>
			</div>



			<!-- 비밀번호 -->

			<div class="col-md-3 offset-md-3">
				<label for="pwd1">비밀번호</label>
			</div>
			<div class="col-md-5 offset-md-3">
				<input type="password" class="form-control" id="pwd1" name="pwd1"
					maxlength="20" placeholder="비밀번호" required>
			</div>

			<div class="col-md-5 offset-md-3">
				<span id="checkPwd1">&nbsp;</span>
			</div>


			<!-- 비밀번호 확인 -->

			<div class="col-md-4 offset-md-3">
				<label for="pwd2">비밀번호 확인</label>
			</div>
			<div class="col-md-5 offset-md-3">
				<input type="password" class="form-control" id="pwd2" maxlength="20"
					placeholder="비밀번호 확인" required>
			</div>

			<div class="col-md-5 offset-md-3">
				<span id="checkPwd2">&nbsp;</span>
			</div>




			<!-- 이메일 -->

			<div class="col-md-9 offset-md-3 ">
				<label for="email">Email</label>
			</div>


			<div class="col-md-5 offset-md-3" style="height:38px;">
					<input type="text" class="form-control" id="email1" name="email" placeholder="이메일" autocomplete="off" required>
				 <span id="at-sign" class="px-1">@</span> 
				 <input type="text" class="form-control" id="email2" name="email" autocomplete="off" required>
			</div>
			<!--  <div>&nbsp;&nbsp;@</div> -->

			<!--  <div class="col-md-2" >
                                    <input type="email" class="form-control" id="email" name="email"  autocomplete="off" required>
        
                            </div> -->




			<div class="col-md-5 offset-md-3">
				<span id="checkEmail">&nbsp;</span>
			</div>



			<!-- 닉네임 -->
			<!-- 팝업창 중복체크 여부 판단을 위한 hidden 타입 요소 -->
			<input type="hidden" name="nickDup" id="nickDup" value="false">
			<div class="col-md-6 offset-md-3">
				<label for="nickName">닉네임</label>
				<p>한글 2~15자리로 입력해주세요.</p>
			</div>
			<div class="col-md-5 offset-md-3">
				<input type="text" class="form-control" id="nickName"
					name="nickName" placeholder="닉네임(2-15자)" required>
			</div>
			
			<div class="col-md-5 offset-md-3">
				<button type="button" class="btn btn-sm btn-block btn-light"
					id="nickDupCheck">닉네임 중복검사</button>
			</div>

			<div class="col-md-5 offset-md-3">
				<span id="checkName">&nbsp;</span>
			</div>

			<div class="col-md-5 offset-md-3">
				<button class="btn btn-lg btn-block" id="signUp-btn" type="submit">회원가입
					완료</button>
					 
			</div>
		</form>
	</div>


	<script>
		 // 아이디 중복 검사용 팝업창 오픈
		$("#idDupCheck").on("click", function() {
			// window.open("주소/경로", "창 이름", "스타일 설정");
			window.open("idDupCheck", "idDupForm", "width=450, height=250");
			// 절대 경로 : ${contextPath}/member/idDupCheck
			// 상대 경로 : idDupCheck

		});
		 
		$("#nickDupCheck").on("click", function() {
			// window.open("주소/경로", "창 이름", "스타일 설정");
			window.open("nickDupCheck", "nickDupForm", "width=450, height=250");
			// 절대 경로 : ${contextPath}/member/idDupCheck
			// 상대 경로 : idDupCheck

		});
		
	</script>
	<script src="${contextPath}/resources/js/member.js"></script>
</body>
</html>
