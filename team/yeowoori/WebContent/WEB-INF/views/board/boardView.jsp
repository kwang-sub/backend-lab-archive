<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath }"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${board.boardTitle }</title>
</head>
<body>
<div id="top"></div>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<style>
        .category-selected{
            height: 30px;
            padding-top: 4px;
            background-color: #A66129;
            color:white;
            font-weight: 500;
            font-size: 14px;
            border-radius: 10px;
            margin-right: 5px ;
        }
        .user-profile{
            width: 20px; height: 20px;
            border: 1px solid gainsboro;
            border-radius: 100px;
            background-repeat: no-repeat;
            background-size: cover;
            display: inline-block;
            vertical-align:middle;
       }
        .user-profile-big{
            width: 40px; height: 40px;
            border: 1px solid gainsboro;
            border-radius: 100px;
            background-repeat: no-repeat;
            background-size: cover;
            display: inline-block;
            vertical-align:middle;
            margin-right: 20px;
       }
       .user-name{
           width:fit-content;
           display: inline-block;
           font-size: 12px;
           margin-left: 5px;
           font-weight: 500;
       }
       .time{
           width:fit-content;
           display: inline-block;
           font-size: 12px;
           margin-left: 30px;
           font-weight: 300;
       }
       #etc-board{
           float: right;
       }
       .comments{
        width:fit-content;
           display: inline-block;
           font-size: 12px;
           margin-right: 10px;
           font-weight: 400;
           color: grey;
       }
       .report{
            color: grey;
           font-size: 12px;
           margin-left: 10px;
           font-weight: 300;
           display: inline-block;
       }
       .text-container{
           display: inline-block;
           width: 80%; height: 35px;
           border : 1px solid rgb(192, 192, 192);
           border-radius: 5px;

       }
       .input-comment{
           width: 85%; height: 32px;
           border: 0px;
           border-radius: 5px;
       }
       .btn-submit{
           background-color: white;
           border: 0px;
           padding: 0px;
           height: 5px;
           color: grey;
           font-weight: 500;
           float: right;
           margin-right: 10px;
           line-height: 2.3;
       }
       #to-top{
           background-color: white;
           width: 45px; height: 70px;
           text-align: center;
           padding-top: 5px;
           line-height: 1.5;
           border-radius: 5px;
           border: 1px solid rgb(214, 214, 214);
           font-size: 12px;
            position: fixed;
            bottom: -25px;
            right: 10px;
       }
       .boardImg{
			width: 200px;
			height: 200px;
			border : 1px solid #ced4da;
			display: inline-block;
			text-align:center;
		}
		.btn-brown{
			background-color :#A66129; 
		}
		
</style>
    <div style="padding :20px;" id="top"></div>
    <div class="container">
        <h5>${board.boardTitle }</h5>
        <div style="padding : 3px;"></div>
        <span class="btn btn-light category-selected" name="category" href="#">${board.categoryName }</span>
        <div style="padding : 5px;"></div>
        <div style="display: inline-block; margin-top: 8px;">
            <div class="user-profile" style="background-image: url(${contextPath }/${board.memberProfile});"></div>
            <div class="user-name">${board.memberNickname }</div>
            <div class="time"><fmt:formatDate value="${board.createDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/> </div>
            
            <c:if test="${board.createDate != board.modifyDate }">
            <div class="time">마지막 수정일 : <fmt:formatDate value="${board.modifyDate }" pattern="yyyy년 MM월 dd일 HH:mm:ss"/></div>
            </c:if>
        
        </div>
        <div id="etc-board">
            <button type="button" class="btn" style="padding-right : 10px;" onclick="fnRequest('like/board')">
                <img id="like-icon" src="https://drive.google.com/uc?id=1e719tW6BVTrSPiZQIxJZ8LUWWuNsx0Lc" height="15px">
            </button>
            <span style="font-size: 12px; color: grey;">${board.likeCount }</span>
            
            <c:if test="${loginMember.memberNo == board.memberNo }">
            <div style="padding : 5px; display: inline-block;"></div>
            <div class="dropdownleft" style="display: inline-block;">
                <button class="btn" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <img src="https://drive.google.com/uc?id=1CBODLkaPUc8Zt4MCTz-uAIBiRgmYjepI" height="15px">
                </button>
                <div class="dropdown-menu " aria-labelledby="dropdownMenuButton">
               
                	<button class="dropdown-item " onclick="fnRequest('updateForm');" >수정하기</button>
                 	<button class="dropdown-item" onclick="if(confirm('정말 삭제하시겠습니까?')){fnRequest('delete');}" >삭제하기</button>
                
                  
                </div>
             </div>
            </c:if>
            
        </div>
        <hr>
        <div style="padding: 10px;"></div>
        
		<c:forEach items="${board.atList }" var="at">
		<c:choose>
			<c:when test="${at.fileLevel == 0 && !empty at.fileNm}">
			<c:set var="img0" value="${contextPath}/${at.filePath}${at.fileNm}"/>
			</c:when>		
			<c:when test="${at.fileLevel == 1 && !empty at.fileNm}">
			<c:set var="img1" value="${contextPath}/${at.filePath}${at.fileNm}"/>
			</c:when>		
			<c:when test="${at.fileLevel == 2 && !empty at.fileNm}">
			<c:set var="img2" value="${contextPath}/${at.filePath}${at.fileNm}"/>
			</c:when>		
			<c:when test="${at.fileLevel == 3 && !empty at.fileNm}">
			<c:set var="img3" value="${contextPath}/${at.filePath}${at.fileNm}"/>
			</c:when>		
		</c:choose>
		</c:forEach>
		<c:if test="${!empty img0 }">
			<h6 style="color:#b9b9b9;">대표 사진</h6>
			<img src="${img0 }" style="max-width:100%; border:1px solid #eee">
			<div style="padding:10px;"></div>
		</c:if>
		<c:if test="${!empty img1 && !empty img2 && !empty img3}">
			<h6 style="color:#b9b9b9;">추가 사진</h6>
		</c:if>
		<c:if test="${!empty img1 }">
		    <div class="mr-2 boardImg">
                <img src="${img1}" style="max-width:100%; max-height:100%;">
            </div>
		</c:if>
		<c:if test="${!empty img2 }">
		    <div class="mr-2 boardImg">
                <img src="${img2}" style="max-width:100%; max-height:100%;">
            </div>
		</c:if>
		<c:if test="${!empty img3 }">
		    <div class="mr-2 boardImg">
                <img src="${img3}" style="max-width:100%; max-height:100%;">
            </div>
		</c:if>
		<c:if test="${!empty img1 && !empty img2 && !empty img3}">
			<div style="padding:10px;"></div>
		</c:if>
		
        <div style="padding :5px;"></div>
        <p>
            ${board.boardContent}
        </p>
        <div style="padding: 10px;"></div>
        <div class="comments">조회</div>
        <div class="comments">${board.readCount }</div>
        <div class="comments">댓글</div>
        <div class="comments">${board.commentCount }</div>
        
        <c:if test="${loginMember.memberNo != board.memberNo }">
        <a href="${contextPath }/report/board?boardNo=${board.boardNo}&memberNo=${loginMember.memberNo}" class="report">신고</a>
        </c:if>
        
        <div style="padding: 20px;"></div>
        
        
		<jsp:include page="comment.jsp"></jsp:include>
        
        <hr>
        <c:if test="${!empty param.area }">
        	<c:set var = "area" value ="&area=${param.area}" />
        </c:if>
       <c:if test="${param.type ==1 }">
       <a href="list?type=${param.type}${area}&cp=${param.cp}" class="btn btn-brown float-right" style="color:white;">목록으로</a>
       </c:if>
       <c:if test="${param.type ==2 }">
       <a href="list2?type=${param.type}${area}&cp=${param.cp}" class="btn btn-brown float-right" style="color:white;">목록으로</a>
       </c:if>
       <c:if test="${param.type ==3 }">
       <a href="list3?type=${param.type}${area}&cp=${param.cp}" class="btn btn-brown float-right" style="color:white;">목록으로</a>
       </c:if>
        
        
        <div id="to-top"><a href="#top" style="color: grey;">▲<br>TOP</a></div>
    </div>
    <div style="padding : 40px;"></div>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
    <form action="#" method="POST" name="requestForm2">
    	<input type="hidden" name="boardNo" value="${param.no}">
    	<input type="hidden" name="cp" value="${param.cp}">
    	<input type="hidden" name="type" value="${param.type}">
    	<input type="hidden" name="area" value="${param.area}">
   		<input type="hidden" name="memberNo" value="${loginMember.memberNo }">
   		
    </form>
    
    <script>
    	function fnRequest(addr){
    		console.log(document.requestForm);
    		document.requestForm2.action = "../board2/"+addr;
    		document.requestForm2.submit();
    		if(addr=='like/board'){
    			$("#like-icon").attr("src", "${contextPath}/resources/img/like.png");
    		}
    		
    	}
    
    </script>
    
</body>
</html>