
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/header.jsp"></jsp:include>
  <div class="container py-5">
    <div class="row mb-3">
      <div>
        <h2>${place.placeName}</h2>
      </div>
      <div>
        <p>${place.placeSummary}</p>
      </div>
      <c:set var="tagList" value="${place.tagList}"/>
      <div class="col-md-12" id="nav">
        <c:forEach items="${tagList}" var="tag">
        <span class="badge bg-primary">${tag.tagName}</span>
        </c:forEach> 
        <a href="#" onclick="history.back()" class="btn btn-outline-secondary btn-lg"  id ="list">목록</a>
        <c:choose>
          <c:when test="${like==0 }">
          <button type="button" class="btn btn-outline-secondary btn-lg btn-like" id="btnLike"  onclick="like_btn();"><i class="bi bi-heart"></i> <span id="likeCount">${place.likeCount}</span></button>
          </c:when>
          <c:otherwise>
          <button type="button" class="btn btn-outline-secondary btn-lg btn-like" id="btnLike" onclick="like_btn();"><i class="bi bi-heart-fill text-danger"></i> <span id="likeCount">${place.likeCount}</span></button>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
    <div class="row">
      <div class="col-md-9">
        <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-inner">
          <c:if test="${!empty place.atList[0] }">
            <div class="carousel-item active">
              <img src="${contextPath}/${place.atList[0].filePath }${place.atList[0].fileName }" class="d-block w-100" alt="...">
            </div>
          </c:if>
          <c:if test="${!empty place.atList[1] }">
            <div class="carousel-item">
              <img src="${contextPath}/${place.atList[1].filePath }${place.atList[1].fileName }" class="d-block w-100" alt="...">
            </div>
          </c:if>
          <c:if test="${!empty place.atList[2] }">
            <div class="carousel-item">
              <img src="${contextPath}/${place.atList[2].filePath }${place.atList[2].fileName }" class="d-block w-100" alt="...">
            </div>
          </c:if>
          <c:if test="${!empty place.atList[3] }">
            <div class="carousel-item">
              <img src="${contextPath}/${place.atList[3].filePath }${place.atList[3].fileName }" class="d-block w-100" alt="...">
            </div>
          </c:if>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
        <br>
        <!-- 글내용 -->
        <div id="content">${place.placeContent }</div>
        <hr>
        <p style="font-size: x-large;">찾아오시는 길 </p>
        <p>${place.placeAddr}</p>
        <!-- 지도 -->
        <div id="map" style="width:100%;height:350px;"></div>
        <br><br>
      </div>
      <div class="col-md-3 border">
        <form action="payMent" method="POST"  onsubmit="return check()">
          <div id="datebox" class="mx-auto">
            <!-- 달력 -->
            <div type="text" name="date" id="date3" size="12"></div>
            <br>
            <!-- 시간선택 -->
            <div id="time">
              <p style="font-size: x-large;">날짜를 선택해주세요</p>
            </div>
            <br>
            <div id="price-zone">
              <p>가격</p>
              <p>
                <fmt:formatNumber value="${place.placeCharge}" pattern="#,###" />원/시간
              </p>
              <p id="price">예약 시간을 선택해주세요</p>
            </div>
            <c:choose>
              <c:when test="${empty loginMember }">
                <a class="btn btn-secondary unLogin" href="#">채팅문의</a>
                <button class="btn btn-primary unLogin" type="button">예약하기</button>
                <br><br>
              </c:when>
              <c:when test="${place.memberNo == loginMember.memberNo }">
                <a class="btn btn-secondary same" href="#">채팅문의</a>
                <button type="button" class="btn btn-primary same">예약하기</button>
                <br><br>
              </c:when>
              <c:otherwise>
                <a class="btn btn-secondary"
                  href="${contextPath}/chat/room?placeMemberNo=${place.memberNo}&placeNo=${place.placeNo}&joinMemberNo=${loginMember.memberNo}">채팅문의</a>
            
                <button class="btn btn-primary" type="submit">예약하기</button>
                <br><br>
              </c:otherwise>
            </c:choose>
            <input type="hidden" name="sumPrice" id="hidden-price">
            <input type="hidden" name="useDate" id="hidden-day">
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- 후기 본문시작 -->
  <jsp:include page="../review/reviewListPlace.jsp"></jsp:include>
  <!-- 후기 본문 끝 -->
        
  <jsp:include page="../../../resources/js/placeViewJs.jsp"></jsp:include>
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a523483cb174903a659b77049c5b0ee7&libraries=services"></script>
  <script type="text/javascript">
    //지도스크립트
    
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };  
    
    // 지도를 생성합니다    
    var map = new kakao.maps.Map(mapContainer, mapOption); 
    
    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();
    
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(addr, function(result, status) {
    
        // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {
    
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    
            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });
    
            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="width:150px;text-align:center;padding:6px 0;">'+name+'</div>'
            });
            infowindow.open(map, marker);
    
            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        } 
    });
  </script>
  <jsp:include page="../common/footer.jsp"></jsp:include>
