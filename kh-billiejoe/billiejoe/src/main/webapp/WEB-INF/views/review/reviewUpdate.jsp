<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" scope="application"
   value="${pageContext.servletContext.contextPath}" />

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>billieJoe</title>
     <!-- Google Webfonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@400;700&display=swap" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
      crossorigin="anonymous"></script>
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
  <!-- Default Style -->
  <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
  <link href="${contextPath}/resources/css/reviewStyle.css" rel="stylesheet">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
  <!-- // header.jsp -->
  <jsp:include page="../common/header.jsp"></jsp:include>
  
    <!-- 본문 시작 -->
  <div class="container py-5">
    <hr>
    <div class="row UpdateWrapper">
      
      <div class="col-3 profile">
        <img src="${contextPath}/${loginMember.memberPic}">
      </div>
      
      <div class="col-9">
        
        <div class="updatePlace">
          <h3>${review.placeName}</h3>
        </div>
        
        <div class="row updateRate">
          
          <div class="col-1 updateAvgRate">
            <p>별점&nbsp;</p>
          </div>
          
          <div class="col-11 starImg">
            <p id="star">
              <a value="1">★</a>
              <a value="2">★</a>
              <a value="3">★</a>
              <a value="4">★</a>
              <a value="5">★</a>
          </div>
            
        </div>
          <hr>
          
          <div>
            <label for="content">내용</label>
          </div>
          
        <form action="updateReview" method="POST" role="form" onsubmit="return reviewValidate();">   
          <div class="contentUpdate">
            <textarea class="form-control" id="reviewContent" name="reviewContent" rows="12" style="resize: none;">${review.reviewContent}</textarea>
          </div>
          
          <div class="btnArea text-end">
            <input type="hidden" name="rate" id="rate"  class="reviewRate">
            <button type="submit" class="btn btn-primary" >수정</button>
            <a class="btn btn-primary" href="${contextPath}/review/reviewList?cp=${param.cp}">목록으로</a>
            <a class="btn btn-primary" href="${param.reviewNo}?cp=${param.cp}">이전</a>
          </div>
          
           <input type="hidden" name="cp" value="${param.cp}">
         <input type="hidden" name="reviewNo" id="reviewNo" value="${review.reviewNo}">
          
         </form> 
        </div>
    </div>
    <hr>
  </div>
  <!-- // 본문 끝 -->

  <script>
    // 별을 클릭했을 때 값이 input에 찍히는 이벤트
      $('#star a').click(function () {
         $(this).parent().children("a").removeClass("on");
         $(this).addClass("on").prevAll("a").addClass("on");
         
            console.log($(this).attr("value"));
            
            var rate = $(this).attr("value");
            
            $(document).ready(function() {
              $('.reviewRate').val(rate);
            });
      
      });

    // 수정 내용 유효성 검사 
      function reviewValidate() {
         if ($("#rate").val().trim().length == 0) {
            swal({
               "icon" : "warning",
               "title" : "별점을 선택해주세요."
            })
            return false;
         }

         if ($("#reviewContent").val().trim().length == 0) {
            swal({
               "icon" : "warning",
               "title" : "내용을 입력해주세요."
            }).then(function() {
               $("#reviewContent").focus();
            });
            return false;
         }
      }
   </script>
  <!-- 본문 끝 -->
   
  <!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>

</body>

</html>
<!-- //footer -->