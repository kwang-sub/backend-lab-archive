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
  <!-- 제이쿼리 -->
   <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  
    <!-- 아임포트 -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>

<body>
  <jsp:include page="../common/header.jsp"></jsp:include><br>
  <div class="container">
  
        <h2>예약하기</h2><br>
        <span class="re-span">
            <img src="${contextPath}/${place.atList[0].filePath}${place.atList[0].fileName}" alt=""  class="re-img">
        </span>
        <span id="reservationContent" class="re-span">
            <p id="title">${place.placeName }</p>
            <p>${place.placeSummary }</p>
            <c:set var="tagList" value="${place.tagList}"></c:set>
            <c:forEach items="${tagList}" var="tag"  >
                <span class="badge bg-primary">${tag.tagName}</span>
            </c:forEach> 
            <br><br><p id="pay"><fmt:formatNumber value="${place.placeCharge}" pattern="#,###" />원/시간</p>
        </span>
        <form action="payComplete" method="POST" id="sub">
	        <hr>
	        <h4>예약정보</h4><br>
	        <p>예약날짜 : ${reservation.useDate }</p>
	        <p>예약 시간 : ${reservation.useStart}시 ~ ${reservation.useEnd}시</p>
	        <p>결제 금액 :</p>
	        <p id="Price"><fmt:formatNumber value="${sumPrice }" pattern="#,###" />원</p><br>
	        <button type="button" class="btn btn-secondary re-btn">취소하기</button>
	        <button type="button" class="btn btn-primary re-btn" id="payment" onclick="iamport();">결제하기</button>
        	<input type="hidden" name="useDate" value="${reservation.useDate}">
        	<input type="hidden" name="useStart" value="${reservation.useStart}">
        	<input type="hidden" name="useEnd" value="${reservation.useEnd}">
        	<input type="hidden" name="payAmount" value="${sumPrice}">
        	<input type="hidden" name="merchantUid"  id="merchantUid">
        	
        </form>
    </div>
    
    
    <br><br><jsp:include page="../common/footer.jsp"></jsp:include>
    <jsp:include page="../../../resources/js/reservationJs.jsp"></jsp:include> 
</body>


</html>