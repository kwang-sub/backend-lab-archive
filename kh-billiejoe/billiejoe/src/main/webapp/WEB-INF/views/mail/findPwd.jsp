<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header.jsp -->
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>findPwd</title>
  <!-- Bootstrap core CSS -->
  
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">


  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
  <link href="dist/css/style.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    
    <!-- sweetalert API 추가 -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<style>
.form-control{
  margin-bottom: 10px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  text-align: center;


}
.form-signin{
    position: relative;
    box-sizing: border-box;
    height: auto;
    padding: 10px;
    font-size: 16px;
    margin-left: 30%;;
}

  #email-btn{
    color : white;
    font-size: medium;
    margin-top: 10px;
    
}

body {
    align-items: center;
    padding-top: 40px;
    padding-bottom: 40px;
}

.form-signin{
  width: 40%;
}

h5, p{
  text-align: center;
}


</style>


<body>
  <jsp:include page="../common/header.jsp"></jsp:include>

<!-- 본문 시작 -->
<!-- 각 페이지의 jsp 파일은 header와 footer를 제외한 본문만 남겨서 작성해주세요. (이클립스에서 보여주는 빨간줄 표시는 무시하셔도 됩니다.) -->
<!-- !!! 강사님은 알아서 합쳐주니 괜찮다고 하셨지만 세 부분으로 나누어 졌을 때 겹치는 곳이 없고 셋이 합쳐 하나의 완성된 html 파일이 되도록 include 되어야 합니다 !!! -->
  <div class="container py-5">


    <form class="form-signin" action="${contextPath}/mail/sendMail" method="post">

      <h5><b>비밀번호 찾기</b></h5><br><br>
      <p>가입시 입력한 이메일과 이름으로 비밀번호 찾기가 가능합니다.</p>
      <p>이메일 주소로 임시 비밀번호를 발송합니다.</p><br>
      <input type="email" id="inputEmail" name="memberEmail" class="form-control" placeholder="ID(이메일)을 입력하세요" required autofocus>

      <input type="text" id="inputName" name="memberName" class="form-control" placeholder="이름을 입력하세요" required autofocus>

      <button class="btn btn-lg btn-block btn-primary" id="email-btn" type="submit">비밀번호 찾기</button>
     
    </form>
  </div>
<!-- // 본문 끝 -->


  
  <%-- 	비밀번호 찾기 실패와 같은 메세지가 서버로 부터 전달되어 온 경우 출력 --%>
	<c:if test="${!empty title }">
		<script>
			swal({
				"icon"  : "${icon}",
				"title" : "${title}",
				"text"  : "${text}"
				
			});
		</script>
		
	</c:if>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
<!-- //footer -->