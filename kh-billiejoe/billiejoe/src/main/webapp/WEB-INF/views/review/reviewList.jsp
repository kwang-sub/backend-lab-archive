<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath}" />

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- header.jsp -->
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>billieJoe</title>
	<!-- Bootstrap core JS -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
		crossorigin="anonymous"></script>
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
    <div class="content-header row">
      <h2 class="col">내가 작성한 후기</h2>
    </div>
    <div class="row">
		
  		<!-- 위치 꼭 이곳에 넣어서 작업하세요! -->
		<jsp:include page="../common/mypageSidebar.jsp"></jsp:include>

        <div class="col-md-9">
                
                <c:choose>
                
                    <%-- 조회된 후기글 목록이 없는 경우 --%>
                    <c:when test="${empty reviewList}">
                    	<div>
	                        <div class="text-center">후기글이 존재하지 않습니다.</div>
                        </div>						
                    </c:when>
                    
                    <%-- 조회된 후기글 목록이 있을 경우 --%>
                    <c:otherwise>
                    
                        <c:forEach items="${reviewList}" var="review">
                            <div class="row listform">

	                                <%-- 후기작성한 장소 대표 이미지 --%>
	                                <div class="col-4">
		                                <div class="imgArea" align="center">
		                                	<img src="${contextPath}/${review.atList[0].filePath}${review.atList[0].fileName}">
		                                </div>
	                                </div>
	
	                                <%-- 작성일, 장소명, 별점, 수정/삭제 버튼 --%>
	                                <div class="col-8">
	                                    
	                                    <br>
	                                    <%-- 작성일 --%>
	                                    <div class="reviewDate">
	                                    	작성일 : &nbsp;
		                                    <fmt:formatDate var="reviewDate" value="${review.reviewDate}"  pattern="yyyy년 M월 d일"/>                          
		                                    <fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
	                                    
	                                    <c:choose>
	
	                                        <%-- 글 작성일이 오늘이 아닐 경우 --%>
	                                        <c:when test="${reviewDate != today}">
	                                        	${reviewDate}
	                                        </c:when>
	                                        
	                                        <%-- 글 작성일이 오늘일 경우 --%>
	                                        <c:otherwise>
	                                            <fmt:formatDate value="${review.reviewDate}"  pattern="HH:mm"/>                          
	                                        </c:otherwise>
	                                        
	                                    </c:choose>
	                                    
	                                    <br>
	                                    </div>
	
	                                    <%-- 후기글 장소명 + 별점 --%>
	                                    <div class="row" >
	                                        
	                                        <%-- 후기남긴 장소명 --%>
	                                        <div class="col-7 div-inline listPlace">
		                                        <%-- <a href="${review.placeNo}?cp=${pagination.currentPage}"> </a>--%>
		                                        	${review.placeName}
	                                        </div>
	                                        
	                                        <%-- 후기 별점 --%>
	                                        <div class="col-5 div-inline text-end" >                                                
	                                        <div class= "listRate">
											      <p class="star">
			                                        <c:choose>
			                                        	<c:when test="${review.reviewRate == 1}">
															<i id="gold">★</i>
															<i>★</i>
															<i>★</i>
															<i>★</i>
															<i>★</i>
			                                        	</c:when>
			                                        	<c:when test="${review.reviewRate == 2}">
			                                        		<i id="gold">★</i> 
															<i id="gold">★</i>
															<i>★</i>
															<i>★</i>
															<i>★</i>
			                                        	</c:when>
			                                        	<c:when test="${review.reviewRate == 3}">
			                                        		<i id="gold">★</i> 
															<i id="gold">★</i>
															<i id="gold">★</i>
															<i>★</i>
															<i>★</i>
			                                        	</c:when>
			                                        	<c:when test="${review.reviewRate == 4}">
			                                        		<i id="gold">★</i> 
															<i id="gold">★</i>
															<i id="gold">★</i>
															<i id="gold">★</i>
															<i >★</i>
			                                        	</c:when>
			                                        	<c:otherwise>
			                                        		<i id="gold">★</i> 
															<i id="gold">★</i>
															<i id="gold">★</i>
															<i id="gold">★</i>
															<i id="gold">★</i>
			                                        	</c:otherwise>
			                                        </c:choose>
			                                	 <i>&nbsp;&nbsp;${review.reviewRate}점</i>
	                                        	</p>
											</div>
	                                   		</div>    
	                                            
	                                    <hr>
	
	                                    <%-- 작성후기 내용 --%>
	                                    <div class="row listContent place">
	                                        <a href="${review.reviewNo}?cp=${pagination.currentPage}">
	                                        	${review.reviewContent}
	                                        </a>
	                                    </div>
	                                    
		                                </div>
                            	</div>
                          </div>

                        </c:forEach>
                    
                    </c:otherwise>
                
                </c:choose>
                

            </div>

            <%---------------------- Pagination start----------------------%>
            <%-- 페이징 처리 시 주소를 쉽게 작성할 수 있도록 필요한 변수를 미리 선언 --%>
            
            <c:set var="pageURL" value="reviewList"  />
            
            <c:set var="prev" value="${pageURL}?cp=${pagination.prevPage}" />
            <c:set var="next" value="${pageURL}?cp=${pagination.nextPage}" />
            
            
            <div class="my-5">
                <ul class="pagination">
            
                    <%-- 현재 페이지가 10페이지 초과인 경우 --%>
                    <c:if test="${pagination.currentPage > pagination.pageSize }">
                    <li><a class="page-link" href="${prev}">&lt;&lt;</a></li>
                    </c:if>
                    
                    <%-- 현재 페이지가 2페이지 초과인 경우 --%>
                    <c:if test="${pagination.currentPage > 2 }">
                    <li><a class="page-link" href="${pageURL}?cp=${pagination.currentPage - 1}">&lt;</a></li>
                    </c:if>
                        
                    <%-- 페이지 목록 --%>
                    <c:forEach var="p" begin="${pagination.startPage}" end="${pagination.endPage}">
                        <c:choose>

                        <c:when test="${p == pagination.currentPage }">
                            <li class="page-item active"><a class="page-link">${p}</a></li>
                        </c:when>
                        
                        <c:otherwise>
                            <li><a class="page-link" href="${pageURL}?cp=${p}">${p}</a></li>
                        </c:otherwise>

                        </c:choose>						
                    </c:forEach>
                    
                    <%-- 현재 페이지가 마지막 페이지 미만인 경우 --%>
                    <c:if test="${pagination.currentPage < pagination.maxPage }">
                    <li><a class="page-link" href="${pageURL}?cp=${pagination.currentPage + 1}">&gt;</a></li>
                    </c:if>
                    
                    <%-- 현재 페이지가 마지막 페이지가 아닌 경우 --%>
                    <c:if test="${pagination.currentPage - pagination.maxPage + pagination.pageSize < 0}">
                    <li><a class="page-link" href="${next}">&gt;&gt;</a></li>
                    </c:if>

                </ul>
            </div>
            <%---------------------- Pagination end----------------------%>
        </div>
    </div>
    
  <!-- // 본문 끝 -->

  <!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>

	<script>
		function fnRequest(addr){
			
			// 현재 문서 내부에 name속성 값이 requestForm인 요소의 action 속성 값을 변경
			document.requestForm.action = addr;
			
			// 현재 문서 내부에 name속성 값이 requestForm인 요소를 제출해라
			document.requestForm.submit();
			
		}
		
	</script>

</body>

</html>
<!-- //footer -->