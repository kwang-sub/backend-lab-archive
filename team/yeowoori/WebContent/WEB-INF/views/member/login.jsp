<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath }"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
 	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
     <title>로그인</title>


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>



    <style>
      #login-btn{
          background-color:  #A66129;
          color : white;
          font-size: medium;
      }

      #login-footer > a{
        color : #A66129;
        font-weight: bold;
        font-size : 13px;
        margin: auto 25px;
      }

      #logo-img{
        width: 180px;
      }
      


    <!-- Custom styles for this template -->
    body {
  height: 100%;
}

body {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: 10px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
    </style>
    <link rel = "stylesheet" href ="${contextPath}/resources/css/login.css">

    
  </head>
  <body class="text-center">
    
<form class="form-signin" action="login" method="post">
  <a href="${contextPath}/homeBoard">
    <img class="mb-4" id="logo-img" src="${contextPath}/resources/img/logo.png">
  </a>

  <input type="text" name="inputId" id="inputId" class="form-control" placeholder="아이디" required autofocus>
  <input type="password" name="inputPw" id="inputPassword" class="form-control" placeholder="비밀번호" required>
  <button class="btn btn-lg btn-block" id="login-btn" type="submit">로그인</button>
  <p id="login-footer">
    <a href="${contextPath}/member/signUp">회원가입</a>
    <a href="${contextPath}/member/findPwd">아이디/비밀번호 찾기</a>
  </p>
</form>

    <c:if test="${!empty title }">
			<script>
				swal({
					"icon" : "${icon}",
					"title" : "${title}",
					"text" : "${text}"
				})
			</script>
			<c:remove var="text"/>
			<c:remove var="icon"/>
			<c:remove var="title"/>
	</c:if>
    
  </body>
</html>