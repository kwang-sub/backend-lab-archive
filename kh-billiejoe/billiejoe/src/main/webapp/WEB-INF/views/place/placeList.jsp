<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/header.jsp"></jsp:include>
  <div class="container py-5">
    <div class="content-header row">
      <h2 class="col">장소 찾기</h2>
      <c:if test="${!empty loginMember }">
      <div class="col text-end">
        <a href="addPlace" class="btn btn-primary">장소 등록</a>
      </div>
      </c:if>
    </div>
    <div class="col-md-9 mx-auto">
      <form action="${contextPath}/place/list" method="get" class="row mb-3">
        <div class="col-md-4">
          <select class="form-select">
            <option selected>지역</option>
            <c:forEach items="${region}" var="region">
              <option value="${region}">${region}</option>
            </c:forEach>
          </select>
        </div>
        <div class="col-md-4">
          <input class="form-control" type="date">
        </div>
        <div class="col-md-4">
          <select class="form-select" name="sort">
            <option value="sortnewest" selected>등록일 최신순</option>
            <option value="sortlike">좋아요 많은 순</option>
          </select>
        </div>
      </form>
      <c:choose>
      <c:when test="${empty placeList}">
        등록된 장소가 없습니다.
      </c:when>
      <c:otherwise>
      <c:forEach items="${placeList}" var="place">
      <div class="card mb-3 place">
        <div class="row g-0">
          <div class="place-thumb col-md-4" style="background-image:url(${contextPath}/resources/images/${place.thumbnail})"></div>
          <div class="col-md-8">
            <div class="card-body">
              <h3 class="card-title"><a href="${place.placeNo}/view?cp=${pagination.currentPage}" class="stretched-link">${place.placeName}</a></h3>
              <p class="card-text">${place.placeSummary}</p>
              <ul class="place-tag list-inline">
                <c:forEach items="${place.tagList}" var="tag">
                <li class="list-inline-item"><a class="badge rounded-pill bg-secondary" href="list?cond=tag&q=${tag.tagName}"><i class="bi bi-hash"></i> ${tag.tagName}</a></li>
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
      </c:forEach>
      </c:otherwise>
      </c:choose>
      
      <c:set var="pageURL" value="list" />
      <c:set var="prev" value="${pageURL}?cp=${pagination.prevPage}" />
      <c:set var="next" value="${pageURL}?cp=${pagination.nextPage}" />
      <nav>
        <ul class="pagination justify-content-center">
         <c:choose>
         <c:when test="${pagination.currentPage > 2 }">
          <li class="page-item">
            <a class="page-link" href="${pageURL}?cp=${pagination.currentPage - 1}" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
         </c:when>
         <c:otherwise>
          <li class="page-item disabled">
            <a class="page-link" href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
         </c:otherwise>
         </c:choose>
				 <c:forEach var="p" begin="${pagination.startPage}" end="${pagination.endPage}">
         <c:choose>
         <c:when test="${p == pagination.currentPage }">
          <li class="page-item active"><a class="page-link" href="#">${p}</a></li>
         </c:when>
         <c:otherwise>
          <li class="page-item"><a class="page-link" href="${pageURL}?cp=${p}">${p}</a></li>
         </c:otherwise>
         </c:choose>
         </c:forEach>
         <c:choose>
         <c:when test="${pagination.currentPage < pagination.maxPage }">
          <li class="page-item">
            <a class="page-link" href="${pageURL}?cp=${pagination.currentPage + 1}${searchStr}" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
         </c:when>
         <c:otherwise>
          <li class="page-item disabled">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
         </c:otherwise>
         </c:choose>
        </ul>
      </nav>
    </div>
  </div>
  <script>
    const searchCond = "${param.cond}";
    $("#placeSearchCond > option").each(function (index, item) {
      if ($(item).val() == searchCond) {
        $(item).prop("selected", true);
      }
    })
	</script>
<jsp:include page="../common/footer.jsp"></jsp:include>