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
            <br><br>
                    <p>(예약일시)</p><br>
                  	<h2> <fmt:formatDate var="chatDate" value="${reservation.reserveDate }" pattern="yyyy년 "/> ${chatDate }</h2> 
                  	<h2> <fmt:formatDate var="chatDate" value="${reservation.reserveDate }" pattern="M월dd일 (E요일)  "/> ${chatDate }</h2> 
                    <p style="font-size: 35px;">${reservation.useStart }:00~${reservation.useEnd }:00</p><br><br>
                    <p>(가격)</p>
                    <p><fmt:formatNumber value="${reservation.placeCharge }" pattern="#,###" />원/시간 x ${map.count }시간</p>
                    <p id = "price"><fmt:formatNumber value="${map.sumPrice }" pattern="#,###" />원</p><br><br>
	                    	
	                  		  <a class="btn btn-secondary" href="${contextPath}/chat/room?placeMemberNo=${place.memberNo}&placeNo=${place.placeNo}&joinMemberNo=${loginMember.memberNo}">채팅문의</a>
	                  		  
	                  		  <c:if test="${reservation.stateNo == 1}">
					                <button type="button" class="btn btn-primary re-del-btn" data-bs-toggle="modal" data-bs-target="#exampleModal">
					                  예약취소
					                </button>
	                  		  </c:if>
	                  		  <c:if test="${reservation.stateNo == 3}">
					                <button type="button" class="btn btn-primary re-del-btn" >
					                  이용완료
					                </button>
	                  		  </c:if>
	                  		  <c:if test="${reservation.stateNo == 4}">
					                <button type="button" class="btn btn-danger re-del-btn" >
					                  예약자 취소
					                </button>
	                  		  </c:if>
	                  		  <c:if test="${reservation.stateNo == 5}">
					                <button type="button" class="btn btn-danger re-del-btn" >
					                 판재자 취소
					                </button>
	                  		  </c:if>
          </div>
        </form>
      </div>
    </div>
  </div>
  
   <!-- Modal -->
     <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content" >
          <div class="modal-header">
            <p >정말로 취소 하시겠습니까?</p>
          </div>
          <!-- <form action="cancelRv" method="POST"> -->
              <div class="modal-footer">
                  <button type="button" class="btn btn-primary modal-btn" id="yesBtn">네</button>
                  <button type="button" class="btn btn-primary modal-btn" data-bs-dismiss="modal">아니오</button>
               
              </div>
         <!--  </form> -->
        </div>
      </div>
    </div>
      
      
    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">환불 규정 </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <div class="modal-body">
                &ensp; &ensp;* 일반대여 시간 기준 (ex. 20:00 대여기준) <br>
                &ensp; &ensp;* 3일 전 취소 시 : 전액 환급 <br>
                &ensp; &ensp;* 1시간 전 취소 시 : 50% 환급 <br>
                &ensp; &ensp;* 당일 1시간 30분 전까지 취소 시 : 10% 환급 (ex. ~18:30) <br>
                &ensp; &ensp;* 당일 1시간 30분 미만 취소 시 : 0% (ex. 18:30 ~ 20:00)  <br>


            </div>
            <div class="modal-footer">
            <form action="cancelRv" method="POST">
	            <button type="button" class="btn btn-secondary" data-dismiss="modal" id="returnBtn">이전</button>
	            <button type="submit" class="btn btn-primary">예약 취소</button>
	             <input type="hidden" name="reserveNo" id="modal-member-no" value="${reservation.reserveNo }">
            </form> 
            </div>
        </div>
        </div>
    </div>  
      
    
    <jsp:include page="../../../resources/js/placeViewJs.jsp"></jsp:include> 
    <script>
    $("#yesBtn").on("click", function(){
    	$("#exampleModal").modal("hide");
    	$("#staticBackdrop").modal("show");
    	
    })
    
    $("#returnBtn").on("click", function(){
    	$("#staticBackdrop").modal("hide");
    })
    </script>
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
   