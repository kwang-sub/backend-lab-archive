<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"></jsp:include>
  <c:set var="pic0" value="https://via.placeholder.com/720x480.png?text=No+Image"/>
  <c:set var="pic1" value="https://via.placeholder.com/720x480.png?text=No+Image"/>
  <c:set var="pic2" value="https://via.placeholder.com/720x480.png?text=No+Image"/>
  <c:set var="pic3" value="https://via.placeholder.com/720x480.png?text=No+Image"/>

  <c:if test="${!empty place}">
    <c:if test="${!empty place.atList[0]}">
      <c:set var="pic0" value="${contextPath}/${place.atList[0].filePath}/${place.atList[0].fileName}"/>
    </c:if>
    <c:if test="${!empty place.atList[1]}">
      <c:set var="pic1" value="${contextPath}/${place.atList[0].filePath}/${place.atList[1].fileName}"/>
    </c:if>
    <c:if test="${!empty place.atList[2]}">
      <c:set var="pic2" value="${contextPath}/${place.atList[0].filePath}/${place.atList[2].fileName}"/>
    </c:if>
    <c:if test="${!empty place.atList[3]}">
      <c:set var="pic3" value="${contextPath}/${place.atList[0].filePath}/${place.atList[3].fileName}"/>
    </c:if>
  </c:if>
  
  <div class="container py-5">
    <div class="content-header row">
      <h2 class="col">장소 등록</h2>
    </div>
    <div class="row">
      <jsp:include page="../common/sidebar.jsp"></jsp:include>
      <div class="col-md-9">
        <form class="needs-validation" id="apps-write" method="post" enctype="multipart/form-data"  role="form" action="write" novalidate>
          <div class="mb-3">
            <label class="form-label" for="inputTitle">장소명</label>
            <input type="text" class="form-control" id="placeName" name="placeName" placeholder="장소명을 입력해주세요" value="${place.placeName}" required>
            <div class="invalid-feedback">
              장소명을 입력해주세요.
            </div>
          </div>
          <div class="row inputPic">
            <div class="col-md-3">
              <div class="mb-3">
                <label for="inputPic0" class="form-label image-preview" id="image-preview0" style="background-image: url(${pic0});">썸네일</label>
                <input class="form-control image-input" type="file" id="inputPic0" name="images" accept="image/*" <c:if test="${empty place.atList[0]}">required</c:if>>
              </div>
            </div>
            <div class="col-md-3">
              <div class="mb-3">
                <label for="inputPic1" class="form-label image-preview" id="image-preview1" style="background-image: url(${pic1});">사진 1</label>
                <input class="form-control image-input" type="file" id="inputPic1" name="images" accept="image/*">
              </div>
            </div>
            <div class="col-md-3">
              <div class="mb-3">
                <label for="inputPic2" class="form-label image-preview" id="image-preview2" style="background-image: url(${pic2});">사진 2</label>
                <input class="form-control image-input" type="file" id="inputPic2" name="images" accept="image/*">
              </div>
            </div>
            <div class="col-md-3">
              <div class="mb-3">
                <label for="inputPic3" class="form-label image-preview" id="image-preview3" style="background-image: url(${pic3});">사진 3</label>
                <input class="form-control image-input" type="file" id="inputPic3" name="images" accept="image/*">
              </div>
            </div>
          </div>
          <div class="mb-3">
            <label class="form-label" for="inputSummary">장소 한 줄 설명</label>
            <input type="text" class="form-control" id="placeSummary" name="placeSummary" placeholder="" value="${place.placeSummary}" required>
            <div class="invalid-feedback">
              장소를 간단하게 소개할 수 있는 설명을 써주세요.
            </div>
          </div>
          <div class="mb-3">
            <label class="form-label" for="inputContent">장소 상세 설명</label>
            <textarea class="form-control" id="placeContent" name="placeContent" required>${place.placeContent}</textarea>
            <div class="invalid-feedback">
              내용을 입력해주세요.
            </div>
          </div>
          <div class="row mb-3 gx-3">
            <label for="inputZipcode" class="mb-1">주소</label>
            <div class="col-md-2 mb-2">
              <div class="d-grid">
                <button class="btn btn-primary" id="findZipCodeBtn" type="button">주소 검색</button>
              </div>
            </div>
            <div class="col-md-10 mb-2">
              <input type="text" class="form-control" id="placeAddr" name="placeAddr" value="${place.placeAddr}" required>
            </div>
          </div>
          <div class="row mb-3 gx-2">
            <label class="form-label" for="inputCharge">시간 당 대여 가격</label>
            <div class="col-md-2">
              <input type="number" class="form-control" id="placeCharge" name="placeCharge" placeholder="" value="${place.placeCharge}" min="0" step="1" required>
              <div class="invalid-feedback">
              시간 당 대여 가격을 입력해주세요.
              </div>
            </div>
            <label for="inputCharge" class="col-md-2 col-form-label">원/시간</label>
          </div>
          <div class="row mb-3">
            <label class="form-label" for="placeAvailableTimeStart">이용 가능 시간</label>
            <div class="col-md-3">
              <div class="input-group">
                <input type="number" class="form-control" id="placeAvailableTimeStart" name="start" min="0" max="23" step="1" placeholder="" value="${pa.start}">
                <span class="input-group-text">~</span>
                <input type="number" class="form-control" id="placeAvailableTimeEnd" name="end" min="0" max="23" step="1" value="${pa.end}">
              </div>
            </div>
          </div>
          <div class="mb-3">
            <label class="form-label" for="inputTag">태그</label>
            <input type="text" class="form-control" id="inputTag" placeholder="스페이스바 키로 추가">
            <div class="invalid-feedback">
              태그를 하나 이상 입력해주세요.
            </div>
            <div class="tag-list my-3 h5">
              <c:if test="${!empty place.tagList}">
              <c:forEach items="${place.tagList}" var="tag">
              <span class="tag badge rounded-pill bg-secondary">${tag.tagName}</span>
              </c:forEach>
              </c:if>
            </div>
          </div>
          <hr>
          <input type="hidden" value="" id="tagString" name="tagString" required>
          <button class="btn btn-outline-primary" type="submit">작성 완료</button>
        </form>
      </div>
    </div>
  </div>
  <script>
    let taggedList = (function() {
      const arr = [];
      <c:forEach items="${place.tagList}" var="tag">
        arr.push('${tag.tagName}')
      </c:forEach>
      return arr;
    })()
  </script>
  <script src="${contextPath}/resources/js/common_form_validation.js" defer></script>
  <script src="${contextPath}/resources/js/hashtag.js" defer></script>
  <script src="${contextPath}/resources/js/ckeditor.js" defer></script>
  <script src="${contextPath}/resources/js/addplace.js" defer></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" defer></script>
  <script src="${contextPath}/resources/js/zipcode.js" defer></script>
<jsp:include page="../common/footer.jsp"></jsp:include>