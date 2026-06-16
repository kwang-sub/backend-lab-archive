<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="common/header.jsp"></jsp:include>
  <div class="container py-5">
 		<h1 class="display-4 text-center">추천 공간</h1>
    <p class="text-center">뜨기 전에 먼저 예약하세요</p>
    <div class="row mb-5">
      <c:choose>
      <c:when test="${empty placeList}">
        등록된 장소가 없습니다.
      </c:when>
      <c:otherwise>
      <c:forEach items="${placeList}" var="place">
      <div class="col-md-6">
        <div class="card mb-3 place">
          <div class="row g-0">
            <div class="place-thumb col-md-4" style="background-image:url(${contextPath}/resources/images/${place.thumbnail})"></div>
            <div class="col-md-8">
              <div class="card-body">
                <h3 class="card-title"><a href="place/${place.placeNo}/view?cp=${placePagination.currentPage}" class="stretched-link">${place.placeName}</a></h3>
                <p class="card-text">${place.placeSummary}</p>
                <ul class="place-tag list-inline">
                  <c:forEach items="${place.tagList}" var="tag">
                  <li class="list-inline-item"><a class="badge rounded-pill bg-secondary" href="place/list?cond=tag&q=${tag.tagName}"><i class="bi bi-hash"></i> ${tag.tagName}</a></li>
                  </c:forEach>
                </ul>
              </div>
              <div class="card-footer">
                <ul class="list-inline place-info">
                  <li class="list-inline-item"><fmt:formatNumber value="${place.placeCharge}" pattern="#,###" />원<small>/시간</small></li>
                  <li class="list-inline-item"><i class="bi bi-chat-square-text"></i> ${place.reviewCount}</li>
                  <li class="list-inline-item"><i class="bi bi-heart-fill"></i> ${place.likeCount}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      </c:forEach>
      </c:otherwise>
      </c:choose>
    </div>
    
    <h1 class="display-4 text-center">최근에 등록된 후기</h1>
    <p class="text-center">이용자들의 생생한 후기를 만나보세요</p>
    <div class="row mb-3">
      <c:choose>
      <c:when test="${empty reviewList}">
        등록된 리뷰가 없습니다.
      </c:when>
      <c:otherwise>
      <c:forEach items="${reviewList}" var="review">
      <div class="col-md-4">
        <div class="card text-white">
          <img src="${contextPath}/resources/images/${review.fileName}" class="card-img">
          <div class="card-img-overlay">
            <h5 class="card-title">${review.placeName}</h5>
            <p class="card-text"><c:forEach begin="0" end="${review.reviewRate}"><i class="bi bi-star-fill text-warning"></i> </c:forEach></p>
            <p class="card-text">${review.reviewContent}</p>
            <ul class="place-tag list-inline">
              <c:forEach items="${review.tagList}" var="tag">
              <li class="list-inline-item"><a class="badge rounded-pill bg-secondary" href="place/list?cond=tag&q=${tag.tagName}"><i class="bi bi-hash"></i> ${tag.tagName}</a></li>
              </c:forEach>
            </ul>
          </div>
        </div>
      </div>
      </c:forEach>
      </c:otherwise>
      </c:choose>
    </div>
  </div>
<jsp:include page="common/footer.jsp"></jsp:include>