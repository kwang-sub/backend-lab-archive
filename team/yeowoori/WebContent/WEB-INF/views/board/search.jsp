<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath }"/>
<c:if test="${!empty param.area }">
     <c:set var="area" value="&area=${param.area}"/>
</c:if>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>검색 화면</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include> 
	<style>
	
        .card{
            border: none;
        }
        .main{
            margin-top: 10px;
        }
        
        .card-head{
            margin-bottom: 15px;
        }
        .card-img-top{
            height: 300px;
        }
        .rounded-circle{
            width: 40px;
            height: 40px;
        }
        .userProple > th > div{
            color: gray ;
        }
        .card-body{
            padding-top: 5px;
            padding-right:5px ;
            padding-left:5px ;
        }
       .card-body>div>div>span>img{
        width:20px;
        height: 70%;
       }
       .category-btn{
        text-align: center;
       }
       .category-selected{
            background-color: #A66129;
            color:white;
            font-weight: 500;
        }
       .category-unselected{
            color: #A66129;
            font-weight: 500;
       }
       .category-btn>button{
           margin-left: 25px;
          
       }
        #write-btn{
            width: 50px;
            height: 50px;
            border: none;
            background-color: #A66129;
            color: white;
            line-height: 20px;
            font-size: 50px;
            padding-bottom: 5px;
            float: right;
        }
       @media(max-width: 992px) { 
            .card-block{
                margin-right: 100px;
            }
        }
       @media(max-width: 770px) { 
            .card-block{
                margin-right: 100px;
                margin-left: auto;
            }
            .category-select{
                position: relative;
                left: 160px;
            }
            .category-btn{
                width: 100%;
                margin: auto;
            }
        }
        @media(max-width: 600px){
            .card-block{
                margin-left: 80px;
            }
            .category-select{
                position: relative;
                left: 60px;
            }
        }

       @media(min-width: 780px) { 
           .category-select { 
               display: none; 
            } 
        }
       @media(max-width: 780px) { 
           .category-btn { 
               display: none; 
            } 
        }
        @media(min-width: 1200px){
            .main{
            margin-left: 45px;

            }
        }
        
        .pagination {
			margin-left: auto; 
			margin-right: auto;
		}
		
		
		.width65{
           width: 65%;
           display: inline-block;
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
           margin-left: 5px;
           font-weight: 300;
       }
       .comments{
        width:fit-content;
           display: inline-block;
           font-size: 12px;
           margin-left: 5px;
           font-weight: 400;
           color: grey;
       }
       .count-comments{
           width:fit-content;
           display: inline-block;
           font-size: 12px;
           margin-left: 5px;
           font-weight: 500;
           color: #A66129;
           margin-right: 10px;
       }
       .report{
        width:fit-content;
           display: inline-block;
           font-size: 12px;
           margin-left: 5px;
           font-weight: 300;
       }
       .q-img{
           width:100px; height: 100px;
           border: 1px solid gainsboro;
           float: right;
           border-radius: 5px;
           background-repeat: no-repeat;
           background-size: cover;
       }
       .padding{
           padding: 10px;
       }
       .page-numbers{
            color: black;
            border: 0px;
       }
       .page-now{
           color: #A66129;
       }
       .color-grey{
           color: rgb(182, 182, 182);
       }

       @media(max-width: 480px){
            #waiting {
                display: none;
            }
            .padding{
               padding: 0px;
           }
       }
       @media(max-width: 520px){
           .padding{
               padding: 0px;
           }
       }
       @media(max-width: 620px){
           .padding{
               padding: 0px;
           }
       }
       @media(max-width: 760px){
           .padding{
               padding: 0px;
           }
       }
	  
    </style>
    <div class="container">
    <div style="padding:10px"></div>
        <c:if test="${!empty tripBoardList}">
        <h5 style="display:inline-block;">여행 게시판</h5>
        <c:if test="${fn:length(tripBoardList)> 6}">
        	<button class="btn" type="button" onclick="fnRequest('trip');"
        		style="padding:0px; float:right; color:grey;">검색결과 더보기</button>
        </c:if> 
        <div style="padding:10px; display:block;"></div>
        <div class="row main">
        <c:forEach items="${tripBoardList }" var="board" begin = "0" end="5">
        
        	<div class="col-sm-6 col-md-4 card-block">
	           <div class="card" style="width: 18rem; border: none;">
	               <div class="card-head">
	                    <table class="userProple">
	                        <th style="padding-right: 5px;">
	                           <img src="${contextPath}/${board.memberProfile}" class=" rounded-circle" alt="">
	                        </th>
	                        <th>
	                            <div>${board.memberNickname }</div>
	                            <div style="font-weight: 400; font-size: 14px;">${board.memberContent }</div>
	                        </th>
	                     </table>
	                </div>
	                    
	                <c:choose>
	                    <c:when test="${empty board.fileName[0] }">
			                 <a href="#">
			                   <div class="card-img-top" style="background-color : #eee;"></div>
			                  </a>
	                    </c:when>
	                 <c:otherwise>
	                    <a href="#">
	                    	<div class="card-img-top" style="background-position: center; background-repeat: no-repeat; background-size: cover; 
	                    			background-image: url(${contextPath}/${board.filePath[0]}${board.fileName[0]});">
	                    	</div>
	                    </a>
	                 </c:otherwise>
	                 </c:choose>
	                    
	                    <div class="card-body">
	                        <div class="row">
	                            <div class="col-xl-12" style="padding-top: 3px; padding-bottom: 5px;">
	                                <span style="padding-left: 5px;">
	                                    <img src="https://drive.google.com/uc?id=1qxz-wF69nTfWlkXBZ70xkWTjO_yjeblU" style="height: 20px; width: auto;">
	                                    <span style="margin-left: 5px; padding-top: 10px;">${board.commentCount }</span>
	                                </span>
	                                <span style="float: right; padding-right: 5px;">
	                                        <img src="https://drive.google.com/uc?id=1e719tW6BVTrSPiZQIxJZ8LUWWuNsx0Lc" style="height: 20px; width: auto;" alt="">
	                                        <span style="margin-left: 5px;">${board.likeCount }</span>
	                                </span>
	                            </div>
	                        </div>
	                        <div style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; height: 70px; width: 100%">

										<c:if test="${fn:length(board.boardContent)>=39}">
											<p>${fn:substring(board.boardContent,0,39) }...</p>
										</c:if>
										<c:if test="${fn:length(board.boardContent)<=39}">
											<p>${board.boardContent }</p>
										</c:if>
									</div>
	                    </div>
	                </div>
           		</div>
	          	
           </c:forEach>
           	</div>	
           
           <hr>
        </c:if>
        
        
        <div style="padding :10px;"></div>
        
        <c:if test="${!empty qBoardList}">
        <h5 style="display: inline-block;">질문 게시판</h5>
        <c:if test="${fn:length(qBoardList)> 5}">
        	<button class="btn" type="button" onclick="fnRequest('question');"
        		style="padding:0px; float:right; color:grey;">검색결과 더보기</button>
        </c:if> 
        <div style="padding:5px "></div>
        <c:forEach items="${qBoardList }" var="board" begin = "0" end="4">
       			<div style="padding : 5px;">
		            <div class="width65">
		                
		                 <a href="view?no=${board.boardNo}&cp=1&type=2${area}"><h6 style="display:inline-block;">${board.boardTitle }</h6></a>
		                <span class="btn btn-light category-selected" 
		                	style="font-size:12px; padding-top:2px; height: 20px; padding-left:7px; padding-right:7px;  margin-left:10px; color:white; ">
		                ${board.categoryName }
		                </span>
		                <a href="view?no=${board.boardNo}&cp=$1&type=2${area}">
			                <div style="font-size: 12px; line-height: 1.4; height:30px; over-flow:hidden; text-overflow: ellipsis; white-space: nowrap;">
			                    ${board.boardContent } 
			                </div>
		                </a>
		                
		                <div class="user-profile" style="background-image: url('${board.memberProfile}');"></div>
		                <div class="user-name">${board.memberNickname}</div>
		                <div class="time">${board.createDate }</div>
		                <div class="comments">댓글</div>
		                <div class="count-comments">${board.commentCount }</div>
		                <a href="#">
		                    <img src="https://drive.google.com/uc?id=1e719tW6BVTrSPiZQIxJZ8LUWWuNsx0Lc" height="15px">
		                    <div class="comments">좋아요</div>
		                </a>
		                <a class="report" href="#">신고</a>
		         	</div>
		            <c:if test="${!empty board.fileName[0] }">
		            	<div class="q-img" style="background-image: url('${contextPath}/${board.filePath[0]}${board.fileName[0]}'); float:right;"></div>
		            </c:if>
		            <div class="padding"></div>
		            <hr>
		        </div>
        </c:forEach>
        </c:if>
        
       
        <div style="padding :10px;"></div>
       
        <c:if test="${!empty withBoardList }">
        <h5 style="display:inline-block;">같이 떠나요 게시판</h5>
         <c:if test="${fn:length(withBoardList)> 5}">
        	<button class="btn" type="button" onclick="fnRequest('with');"
        		style="padding:0px; float:right; color:grey;">검색결과 더보기</button>
        </c:if> 
        <div style="padding:5px"></div>
        <c:forEach items="${withBoardList }" var="board" begin = "0" end="4">
        		<div style="padding : 5px;">
		            <div class="width65">
		                
		                <a href="view?no=${board.boardNo}&cp=1&type=3${area}"><h6 style="display:inline-block;">${board.boardTitle }</h6></a>
		                <span class="btn btn-light category-selected" 
		                	style="font-size:12px; padding-top:2px; height: 20px; padding-left:7px; padding-right:7px;  margin-left:10px; color:white; ">
		                ${board.categoryName }
		                </span>
		                <a href="view?no=${board.boardNo}&cp=1&type=3${area}">
			                <div style="font-size: 12px; line-height: 1.4; height:30px; over-flow:hidden; text-overflow: ellipsis; white-space: nowrap;">
			                    ${board.boardContent } 
			                </div>
		                </a>
		                
		                <div class="user-profile" style="background-image: url('${board.memberProfile}');"></div>
		                <div class="user-name">${board.memberNickname}</div>
		                <div class="time">${board.createDate }</div>
		                <div class="comments">댓글</div>
		                <div class="count-comments">${board.commentCount }</div>
		                <a href="#">
		                    <img src="https://drive.google.com/uc?id=1e719tW6BVTrSPiZQIxJZ8LUWWuNsx0Lc" height="15px">
		                    <div class="comments">좋아요</div>
		                </a>
		                <a class="report" href="#">신고</a>
		         	</div>
		            <c:if test="${!empty board.fileName[0] }">
		            	<div class="q-img" style="background-image: url('${contextPath}/${board.filePath[0]}${board.fileName[0]}'); float:right;"></div>
		            </c:if>
		            <div class="padding"></div>
		            <hr>
		        </div>
        
        </c:forEach>
        </c:if>
        <c:if test="${empty tripBoardList  && empty qBoardList && empty withBoardList}">
        <h3 style="text-align:center;">검색 결과가 없습니다.</h3>
        <div style="padding:10px;"></div>
        <div style="text-align:center;">
        	<a href="${contextPath}" class="btn" style="background-color: #A66129; color:white;">메인 페이지로 이동하기</a>
        </div>
        </c:if>
        		
        
    </div>
            
	<div style="padding : 40px;"></div>
	
	<form action="#" method="GET" name="requestForm3">
		<input type="hidden" name="search" value="${search}">
	</form>
	
	
	<script>
		function fnRequest(addr){
			document.requestForm3.action = "../search/"+addr;
    		document.requestForm3.submit();
		}
	</script>
	
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

</body>
</html>