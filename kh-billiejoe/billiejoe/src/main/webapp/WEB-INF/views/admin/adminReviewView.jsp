<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>billieJoe admin</title>
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
  
  <!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>

<body>
  <main>
  
  <jsp:include page="../common/adminSidebar.jsp"></jsp:include>
  
  <!-- 본문 시작 -->
  <div class="container py-5">
    <div class="row viewWrapper">
       <div class="col-3 profile content">
         <img src="${contextPath}/${loginMember.memberPic}">
       </div>

       <div class="col-9 content">
         
          <div class="row placeDate">
            <h3>${reviewView.placeName}</h3>
            <%-- 작성일 --%>
            <div class="reviewDate">
            작성일 : &nbsp;
             <fmt:formatDate var="reviewDate" value="${reviewView.reviewDate}"  pattern="yyyy-MM-dd"/>                          
             <fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
            
            <c:choose>

                <%-- 글 작성일이 오늘이 아닐 경우 --%>
                <c:when test="${reviewDate != today}">
                	${reviewDate}
                </c:when>
                
                <%-- 글 작성일이 오늘일 경우 --%>
                <c:otherwise>
                    <fmt:formatDate value="${reviewView.reviewDate}"  pattern="HH:mm"/>                          
                </c:otherwise>
                
            </c:choose>
          </div>

          <div class="row rate">
            
            <div class="col-11 ">
              <p class="star">
              <c:choose>
	              <c:when test="${reviewView.reviewRate==1}">
		              <i id="gold">★</i>
		              <i>★</i>
		              <i>★</i>
		              <i>★</i>
		              <i>★</i>
	              </c:when>
	              <c:when test="${reviewView.reviewRate==2}">
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i>★</i>
		              <i>★</i>
		              <i>★</i>
	              </c:when>
	              <c:when test="${reviewView.reviewRate==3}">
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i>★</i>
		              <i>★</i>
	              </c:when>
	              <c:when test="${reviewView.reviewRate==4}">
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i>★</i>
	              </c:when>
	              <c:otherwise>
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i id="gold">★</i>
		              <i id="gold">★</i>
	              </c:otherwise>
              </c:choose>
            </div>
          <hr>
          
	          <div class="viewContent">
				${reviewView.reviewContent}
	          </div>
	
          </div>
      </div>
      
      <hr>
      
      <div class="row">
	      <div class="col viewBtnArea text-start">
	        <a href="${contextPath}/admin/review?cp=${param.cp}" class="btn btn-primary listBtn">목록으로</a>
	      </div>
	      
	      <div class="col viewBtnArea text-end">
		        <a class="btn btn-primary listBtn" onclick="fnDeleteRequest('deleteReview');">삭제</a>
	      </div>
      </div>
    </div>
  </div>
  </div>
  
  <!-- // 본문 끝 -->

	<form action="#" method="POST" name="requestForm">
		<input type="hidden" name="reviewNo" value="${reviewView.reviewNo}">
		<input type="hidden" name="cp" value="${param.cp}">
	</form>
	
	<script>
	
		function fnRequest(addr){
			document.requestForm.action = addr;
			document.requestForm.submit();
		}
		
		function fnDeleteRequest(addr){

                swal({
                    title: "후기 삭제",
                    text: "정말 삭제하시겠습니까?",
                    icon: "info",
                    buttons: [
                        '아니요',
                        '네'
                    ],
                    dangerMode: true,
                }).then(function (isConfirm) {
                    if (isConfirm) {
						document.requestForm.action = addr;
						document.requestForm.submit();
                    } 
                })
            }
	</script>
	
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
	</script>
	

</body>

</html>
