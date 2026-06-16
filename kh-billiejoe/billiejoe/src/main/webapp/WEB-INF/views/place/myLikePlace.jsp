<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- 본문 시작 -->
	<div class="container py-5">
		<div class="content-header row">
			<h2 class="col">내가 찜한 장소</h2>
		</div>
		<div class="row">
			<jsp:include page="../common/mypageSidebar.jsp"></jsp:include>
			<div class="col-md-9">
				<c:forEach items="${myLikePlaceList}" var="likePlace">
					<div class="card mb-3 place">
						<div class="row g-0">
							<div class="place-thumb col-md-4"
								style="background-image:url(${contextPath}/${likePlace.atList[0].filePath }${likePlace.atList[0].fileName })"></div>
							<div class="col-md-8">
								<div class="card-body">
									<h3 class="card-title">
										<a href="${contextPath }/place/${likePlace.placeNo }/view"
											class="stretched-link">${likePlace.placeName}</a>
									</h3>
									<p class="card-text">${likePlace.placeSummary}</p>
									<ul class="place-tag list-inline">
										<c:forEach items="${likePlace.tagList}" var="tag">
											<li class="list-inline-item"><a
												class="badge rounded-pill bg-secondary" href="#"><i
													class="bi bi-hash"></i>${tag.tagName}</a></li>
										</c:forEach>
									</ul>
								</div>
								<div class="card-footer">
									<ul class="list-inline place-info">
										<li class="list-inline-item"><fmt:formatNumber
												value="${likePlace.placeCharge }" pattern="#,###" />원<small>/시간</small></li>
										<li class="list-inline-item"><i
											class="bi bi-chat-square-text"></i> ${likePlace.reviewCount}</li>
										<li class="list-inline-item"><i class="bi bi-heart-fill"></i>
											${likePlace.likeCount }</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<br>
				</c:forEach>
			</div>
			<!-- 페이징 -->
			<nav aria-label="Page navigation" id="navigation">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="${prev}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<%-- 페이지 목록 --%>
					<c:forEach var="p" begin="${pagination.startPage}"
						end="${pagination.endPage}">

						<c:choose>
							<c:when test="${p == pagination.currentPage }">
								<li class="page-item active"><a class="page-link">${p}</a></li>
							</c:when>

							<c:otherwise>
								<li><a class="page-link" href="${pageURL}?cp=${p}${sort}">${p}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="${next }"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
