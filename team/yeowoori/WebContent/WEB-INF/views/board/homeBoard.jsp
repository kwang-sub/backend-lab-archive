
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath }"/>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여기우리, 여행우리</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <style>
        .bg-img{
            position: absolute;
            top: 0;
            left: 0;
            background-color: #777;
            width: 70%;
            height: 60%;
            margin-top: 2rem;
            margin-left: 15%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
        /* Carousel base class */
        .carousel {
        margin-bottom: 4rem;
        }
        /* Since positioning the image, we need to help out the caption */
        .carousel-caption {
        bottom: 3rem;
        z-index: 10;
        }
        /* Declare heights because of positioning of img element */
        .carousel-item {
        height: 32rem;
        }
        .carousel-item > img {
        position: absolute;
        top: 0;
        left: 0;
        min-width: 100%;
        height: 32rem;
        }

        .carousel-control-next,
        .carousel-control-prev  {
            filter: invert(40%);
        }

        .notice-bar{
            background-color:#f8f8f8;   
            height: 30px;
            margin-bottom: 10px;
        }
        .notice-content{
            line-height: 2;
            text-align: center;
            white-space: no-wrap;
        	overflow : hidden;
        	height: 30px;
        }
        .notice-content > a{
            color: black;
        }
        .main-board{
            max-width: 100%;
            margin-bottom: 10px;
        }
        .more{
            width: 70px; height: 25px;
            background-color: #eee;
            border-radius: 5px;
            line-height: 1.8;
            text-align: center;
            float: right;
            color: grey;
            font-size: 14px;
            margin-right: 10px;
        }

        @media (min-width: 40em) {
            .carousel-caption p {
                margin-bottom: 1.25rem;
                font-size: 1.25rem;
                line-height: 1.4;
            }
        }
        .overflow{
        	height: 120px;
        	white-space: no-wrap;
        	overflow : hidden;
        	
        }
        
    </style>
    
    <c:if test="${!empty param.area }">
	<c:set var="area" value="&area=${param.area}"/>    
	</c:if>
	
    <div style="padding: 10px;"></div>
    <h6 style="padding-left: 8%; margin-bottom: 20px; display:inline-block;">여행 인기 게시글</h6>
    <a href="${contextPath }/board/list?type=1${area}" style="color:grey; float:right; margin-right:100px;">여행 게시글 더보기</a>

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false"><title> </title><rect width="100%" height="100%" fill="#f8f8f8"/><text x="50%" y="50%" fill="#777" dy=".3em"> </text></svg>
          <div class="container">
              <a href="board/view?no=${boardList[0].boardNo}&cp=1&type=1${area}">
                    <div class="bg-img" style="background-image: url(${contextPath}/${boardList[0].filePath[0]}${boardList[0].fileName[0]});"></div>
                    <div class="carousel-caption text-left overflow">
	                    <h3 style="color: black;">${boardList[0].boardTitle }</h3>
	                    <c:set var="boardContent" value="${boardList[0].boardContent }"/>
	                    <p style="color: black;">
	                    
	                    <c:if test="${fn:length(boardContent) < 101 }">
	                    ${boardContent}
	                    </c:if>
	                    <c:if test="${fn:length(boardContent) > 100}">
	                    ${fn:substring(boardContent,0,100) }...
	                    </c:if>
	                    
	                    </p>
                    </div>
               </a>
          </div>
        </div>
        <div class="carousel-item">
          <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false"><title> </title><rect width="100%" height="100%" fill="#f8f8f8"/><text x="50%" y="50%" fill="#777" dy=".3em"> </text></svg>
          
          <div class="container">
                <a href="board/view?no=${boardList[1].boardNo}&cp=1&type=1${area}">
                    <div class="bg-img" style="background-image: url(${contextPath}/${boardList[1].filePath[0]}${boardList[1].fileName[0]});"></div>
                    <div class="carousel-caption text-left overflow">
                    <h3 style="color: black;">${boardList[1].boardTitle }</h3>
                    <c:set var="boardContent" value="${boardList[1].boardContent }"/>
                    <p style="color: black;">
                    <c:if test="${fn:length(boardContent) < 101 }">
                    ${boardContent}
                    </c:if>
                    <c:if test="${fn:length(boardContent) > 100}">
                    ${fn:substring(boardContent,0,100) }...
                    </c:if>
                    </p>
                    </div>
               </a>
          </div>
        </div>
        <div class="carousel-item">
          <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false"><title> </title><rect width="100%" height="100%" fill="#f8f8f8"/><text x="50%" y="50%" fill="#777" dy=".3em"> </text></svg>
          
          <div class="container">
                <a href="board/view?no=${boardList[2].boardNo}&cp=1&type=1${area}">
                    <div class="bg-img" style="background-image: url(${contextPath}/${boardList[2].filePath[0]}${boardList[2].fileName[0]});"></div>
                    <div class="carousel-caption text-left overflow">
                   <h3 style="color: black;">${boardList[2].boardTitle }</h3>
                    <c:set var="boardContent" value="${boardList[2].boardContent }"/>
                    <p style="color: black;">
                    <c:if test="${fn:length(boardContent) < 101 }">
                    ${boardContent}
                    </c:if>
                    <c:if test="${fn:length(boardContent) > 100}">
                    ${fn:substring(boardContent,0,100) }...
                    </c:if>
                    </p>
                    </div>
                </a>
           </div>
      </div>
      <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
    </div>

	<c:if test="${!empty noticeList }">
	<c:forEach items="${noticeList}" var="notice">
		<div class="notice-bar">
            <div class="notice-content">
                <a href="${contextPath}/board/list4?type=4">
                    <img src="${contextPath }/resources/img/megaphone.png" style="width:20px">
                    <span style="padding-left: 10px;">
						<b>${notice.noticeTitle}</b>&nbsp;
						<c:if test="${fn:length(notice.noticeContent) > 20}">
						${fn:substring(notice.noticeContent,0,20) }...
						</c:if>
						<c:if test="${fn:length(notice.noticeContent) < 21}">
						${notice.noticeContent}
						</c:if>
                    </span>
                </a>
            </div>
        </div>
	</c:forEach>
	</c:if>

    
        <div style="padding: 30px;"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <div class="main-board">
                        <h6 style="margin-left:10px; margin-bottom: 10px; display: inline-block;">질문</h6>
                         <c:if test="${!empty qBoardList }">
                         <a class="more" href="${contextPath}/board/list2?type=2${area}">더보기</a>
                         </c:if>
                    </div>
                    <ul class="list-group">
                    <c:if test="${!empty qBoardList }">
                       	<c:forEach items="#{qBoardList}" var="qboard">
                       	
                    	<li class="list-group-item"><a href="board/view?no=${qboard.boardNo}&cp=1&type=2${area}">
                    	<c:if test="${fn:length(qboard.boardTitle) <21 }">
                    	${qboard.boardTitle}
                    	</c:if>
                    	<c:if test="${fn:length(qboard.boardTitle) > 20}">
                    	${fn:substring(qboard.boardTitle,0,20) }...
                    	</c:if>
                    	</a></li>
                    	
                    	</c:forEach>
                    </c:if>
                    <c:if test="${empty qBoardList }">
                    	<li class="list-group-item">등록된 게시글이 없습니다.</li>
                    </c:if>
                      </ul>
                </div>
                <div style="padding : 20px;"></div>
                <div class="col-sm">
                    <div class="main-board">
                        <h6 style="margin-left:10px; margin-bottom: 10px; display: inline-block;">같이 떠나요</h6>
                         <c:if test="${!empty withBoardList }">
                         <a class="more" href="${contextPath}/board/list3?type=3${area}">더보기</a> 
                         </c:if>
                    </div>
                    <ul class="list-group">
                    <c:if test="${!empty withBoardList }">
                        <c:forEach items="#{withBoardList}" var="withBoard">
                        
                    	<li class="list-group-item"><a href="board/view?no=${withBoard.boardNo}&cp=1&type=3${area}">
						<c:if test="${fn:length(withBoard.boardTitle) <21 }">
                    	${withBoard.boardTitle}
                    	</c:if>
                    	<c:if test="${fn:length(withBoard.boardTitle) > 20}">
                    	${fn:substring(withBoard.boardTitle,0,20) }...
                    	</c:if>
						</a></li>
						
                    	</c:forEach>
                    </c:if>
                    <c:if test="${empty withBoardList }">
                    	<li class="list-group-item">등록된 게시글이 없습니다.</li>
                    </c:if>
                      </ul>
                </div>
            </div>
        </div>
        <div style="padding:40px;"></div>

  
  
   
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>
    
    
