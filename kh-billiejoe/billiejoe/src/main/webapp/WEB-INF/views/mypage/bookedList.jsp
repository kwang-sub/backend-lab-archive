<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"></jsp:include>
  <div class="container py-5">
    <div class="content-header row">
      <h2 class="col">예약 접수 내역</h2>
    </div>
    <div class="row">
      <jsp:include page="../common/mypageSidebar.jsp"></jsp:include>
      <div class="col-md-9">
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">이름</th>
              <th scope="col">전화번호</th>
              <th scope="col">장소명</th>
              <th scope="col">예약일시</th>
              <th scope="col">대여시간</th>
              <th scope="col">진행상태</th>
              <!-- <th scope="col">예약취소</th> -->
            </tr>
          </thead>
          <tbody>
            <c:choose>
            <c:when test="${empty resList}">
              <tr><td colspan="7">접수된 예약이 없습니다.</td></tr>
            </c:when>
            <c:otherwise>
            <c:forEach items="${resList}" var="res">
            <tr>
              <td>${res.guestName}</td>
              <td>${res.guestPhone}</td>
              <td><a href="${contextPath}/place/${res.placeNo}/view" target="_blank">${res.placeName}</a></td>
              <td>${res.reserveDate}</td>
              <td>${res.useStart}시 ~ ${res.useEnd}시</td>
              <td>${res.stateName}</td>
<%--               <td>
                <c:if test="${res.stateNo == 1}">
                <a href="${res.reserveNo}/cancel" class="btn btn-danger">예약취소</a>
                </c:if>
              </td> --%>
            </tr>
            </c:forEach>
            </c:otherwise>
            </c:choose>
          </tbody>
        </table>
        
        <c:set var="pageURL" value="bookedList" />
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
              <a class="page-link" href="${pageURL}?cp=${pagination.currentPage + 1}" aria-label="Next">
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
  </div>
  <jsp:include page="../common/footer.jsp"></jsp:include>