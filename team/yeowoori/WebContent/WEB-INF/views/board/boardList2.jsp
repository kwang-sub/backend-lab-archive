
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath }"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>질문게시판</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<style>
     #q-search{
            height: 300px;
            background-color: #f8f8f8;
            text-align: center;
            padding-top: 40px;
            padding-bottom: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
     }
     #q-search input{
            width: 80%; height:35px;
            max-width: 600px;
            background-image: url('https://drive.google.com/uc?id=1ekFsz_P2MZWoMUt0QyvZjPjAm2fx308R');
            background-repeat: no-repeat;
            background-size: 20px;
            background-position: 5px center;
            border : 1px solid rgb(182, 182, 182);
            border-radius: 5px;
            padding-left: 40px;
            font-size: 14px;
     }
       .category-selected{
            background-color: #A66129;
            color:white;
            font-weight: 500;
            margin-right:5px;
             border-radius : 15px;
        }
       .category-unselected{
      		background-color : #eee;
            color: #A66129;
            font-weight: 500;
            margin-right:5px;
            border-radius : 15px;
       }
       .category-btn>button{
           margin-left: 25px;
       }
       .width65{
           width: 65%;
           display: inline-block;
       }
       .user-profile{
            width: 30px; height: 30px;
            border: 1px solid gainsboro;
            border-radius: 100px;
            background-repeat: no-repeat;
            background-size: cover;
            background-position:center;
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
       .pagination {
			margin-left: auto; 
			margin-right: auto;
	   }
	   .select:active{
	   	     background-color: #A66129;
            color:white;
	   }
</style>


    <div class="container">
        <div id="q-search">
            <h4>질문게시판</h4>
            <div style="padding: 10px;"></div>
            <form style="width: 100%; " method="GET" action="${contextPath }/board/list2">
            	<input type="hidden" name="type" value="${pagination.boardType }">
                <input type="search" name = "search"placeholder="궁금한 것을 물어보세요."></input>
            </form>
            <div style="padding : 10px;"></div>
            <div style="width: 80%; max-width: 600px; text-align: left; line-height: 2.3;">
            <c:if test="${!empty param.area }">
            <c:set var="area" value="&area=${param.area}"/>
            </c:if>
            <c:if test="${empty param.category }">
                <a class="btn category-selected" href="/yeowoori/board/list2?type=2${area}" style="color:white;">전체</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=1${area}" style="color:#A66129;" >분위기맛집</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=2${area}" style="color:#A66129;" >산책코스</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=3${area}" style="color:#A66129;">커플데이트</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=4${area}" style="color:#A66129;" >카페투어</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=5${area}" style="color:#A66129;" >기타</a>
            </c:if>
            <c:if test="${param.category ==1}">
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2${area}"  style="color:#A66129;">전체</a>
                <a class="btn category-selected" href="/yeowoori/board/list2?type=2&category=1${area}" style="color:white;" >분위기맛집</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=2${area}" style="color:#A66129;" >산책코스</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=3${area}" style="color:#A66129;">커플데이트</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=4${area}" style="color:#A66129;" >카페투어</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=5${area}" style="color:#A66129;" >기타</a>
            </c:if>
            <c:if test="${param.category ==2}">
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2${area}"  style="color:#A66129;">전체</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=1${area}" style="color:#AA66129;" >분위기맛집</a>
                <a class="btn category-selected" href="/yeowoori/board/list2?type=2&category=2${area}" style="color:white;" >산책코스</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=3${area}" style="color:#A66129;">커플데이트</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=4${area}" style="color:#A66129;" >카페투어</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=5${area}" style="color:#A66129;" >기타</a>
            </c:if>
            <c:if test="${param.category ==3}">
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2${area}"  style="color:#A66129;">전체</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=1${area}" style="color:#A66129;" >분위기맛집</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=2${area}" style="color:#A66129;" >산책코스</a>
                <a class="btn category-selected" href="/yeowoori/board/list2?type=2&category=3${area}" style="color:white;">커플데이트</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=4${area}" style="color:#A66129;" >카페투어</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=5${area}" style="color:#A66129;" >기타</a>
            </c:if>
            <c:if test="${param.category ==4}">
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2${area}"  style="color:#A66129;">전체</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=1${area}" style="color:#A66129;" >분위기맛집</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=2${area}" style="color:#A66129;" >산책코스</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=3${area}" style="color:#A66129;">커플데이트</a>
                <a class="btn category-selected" href="/yeowoori/board/list2?type=2&category=4${area}" style="color:white;" >카페투어</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=5${area}" style="color:#A66129;" >기타</a>
            </c:if>
            <c:if test="${param.category ==5}">
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2${area}"  style="color:#A66129;">전체</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=1${area}" style="color:#A66129;" >분위기맛집</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=2${area}" style="color:#A66129;" >산책코스</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=3${area}" style="color:#A66129;">커플데이트</a>
                <a class="btn category-unselected" href="/yeowoori/board/list2?type=2&category=4${area}" style="color:#A66129;" >카페투어</a>
                <a class="btn category-selected" href="/yeowoori/board/list2?type=2&category=5${area}" style="color:white;" >기타</a>
            </c:if>
            </div>
        </div>

        <div>
            <div style="display: inline-block; margin-top: 40px; width: fit-content;">
                <div style="font-size: 12px; color:grey; font-weight: 300; width: fit-content; ">
                    정렬
                </div>
                <div style="font-weight: 500; font-size: 18px;">최신순</div>
                
            </div>
            <div style="float: right; margin-top: 40px;">
              <c:if test="${!empty loginMember }">
              	<a href="${contextPath }/boardwrite?cp=&type=2" class="btn btn-light" style="background-color:#A66129; color:white; width: 100px; ">
                    질문하기
                </a>
              </c:if>
                
            </div>
        </div>

        <hr>
        
     
        <c:if test="${empty boardList }">
        		<h1 style="text-align: center;">작성된 글이 없습니다.</h1>
        </c:if>
        
    	<c:if test="${!empty boardList }">
        		<c:forEach items="${boardList }" var="board">
        		
        		 <div style="padding : 5px;">
		            <div class="width65">
		                
		                <a href="../board/view?no=${board.boardNo}&cp=${pagination.currentPage}&type=2${area}"><h6 style="display:inline-block;">${board.boardTitle }</h6></a>
		                <span class="btn btn-light category-selected" 
		                	style="font-size:12px; padding-top:2px; height: 20px; padding-left:7px; padding-right:7px;  margin-left:10px; color:white; ">
		                ${board.categoryName }
		                </span>
		                <a href="view?no=${board.boardNo}&cp=${pagination.currentPage}&type=2${area}">
			                <div style="font-size: 12px; line-height: 1.4; height:15px; margin-bottom:7px; overflow:hidden;">
			                    ${board.boardContent } 
			                </div>
		                </a>
		                <div class="user-profile" style="background-image: url(${contextPath}${board.memberProfile});"></div>
		                <div class="user-name">${board.memberNickname}</div>
		                <div class="time">${board.createDate }</div>
		                <div class="comments">댓글</div>
		                <div class="count-comments">${board.commentCount }</div>
		                <a href="${contextPath }/board2/like/board?boardNo=${board.boardNo}&memberNo=${loginMember.memberNo}">
		                    <img src="https://drive.google.com/uc?id=1e719tW6BVTrSPiZQIxJZ8LUWWuNsx0Lc" height="15px">
		                    <div class="comments">좋아요</div>
		                </a>
		                <c:if test="${board.memberNickname ne loginMember.memberNickname }">
		                
		              	  <a class="report" href="${contextPath }/report/board?boardNo=${board.boardNo}&memberNo=${loginMember.memberNo}">신고</a>
		                </c:if>
		         	</div>
		            <c:if test="${!empty board.fileName[0] }">
		            	<div class="q-img" style="background-image: url(${contextPath}/${board.filePath[0]}${board.fileName[0]}); float:right;"></div>
		            </c:if>
		            <div class="padding"></div>
		            <hr>
		        </div>
		      	<c:set var="boardNo" value="${board.boardNo }"/>
        		</c:forEach>
        </c:if>

		<div style="padding : 10px;"></div>



		<!-- 페이지네이션  -->

		 	<c:if test="${!empty param.area && empty param.category  }">
         	<c:set var = "areaCategory" value="&area=${param.area }" />
           	</c:if>
           			
           	<c:if test="${empty param.area && !empty param.category  }">
           	<c:set var = "areaCategory" value="&category=${param.category }" />
           	</c:if>
           			
 			<c:if test="${!empty param.area && !empty param.category }">
           	<c:set var = "areaCategory" value="&category=${param.category }&area=${param.area }" />
 			</c:if>  
 			
            <c:set var = "pageURL" value="list2?type=${pagination.boardType}${areaCategory}"/>
            
            <c:if test="${!empty param.memberNo && empty param.like }">
       			        
            	<c:set var = "prev" value="${contextPath }/board/list2/my?memberNo=${loginMember.memberNo}&type=2&&cp=${pagination.prevPage}"/>
       		</c:if>
            <c:if test="${empty param.memberNo }">
           		 <c:set var = "prev" value="${pageURL }&&cp=${pagination.prevPage}"/>
       			        
       		</c:if>
       		<c:if test="${!empty param.like }">
            	<c:set var = "prev" value="${contextPath }/board/list2/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=2&&cp=${pagination.prevPage}"/>
           	</c:if>
       		
       		
       		<c:if test="${!empty param.like }">
	            <c:set var = "next" value="${contextPath }/board/list2/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=2&&cp=${pagination.nextPage}"/>
           	</c:if>
       		
            <c:if test="${!empty param.memberNo && empty param.like }">
       			        
	            <c:set var = "next" value="${contextPath }/board/list2/my?memberNo=${loginMember.memberNo}&type=2&&cp=${pagination.nextPage}"/>
       		</c:if>
            <c:if test="${empty param.memberNo }">
	            <c:set var = "next" value="${pageURL }&&cp=${pagination.nextPage}"/>
       			        
       		</c:if>
           		
            
            <!-- 페이지 버튼 누를시 쿼리스트링 값 유지 되는법 확인하기  -->
            
           <div class="pagination-box row" style=";">
           	<ul class="pagination" style="text-align = center;" align="center">
           		<c:if test="${pagination.currentPage>pagination.pageSize }">
						<li><a class="page-link" href="${prev }">&lt;&lt;</a></li>          		
 					 
           		</c:if>	
           		
           		<c:if test="${pagination.currentPage>2 }">
						
						<c:if test="${empty param.memberNo &&empty like }">
							<li><a class="page-link" href="${pageURL}&cp=${pagination.currentPage-1}">&lt;</a></li>
           						
           				</c:if>                 		
						<c:if test="${!empty param.memberNo&& empty param.like }">
							<li><a class="page-link" href="${contextPath }/board/list2/my?memberNo=${loginMember.memberNo}&type=2&cp=${pagination.currentPage-1}">&lt;</a></li>
           						
           				</c:if>
           				<c:if test="${!empty param.like }">
							<li><a class="page-link" href="${contextPath }/board/list2/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=2&cp=${pagination.currentPage-1}">&lt;</a></li>
           						
           				</c:if>
           				                 		
           		</c:if>	
           		
           		<c:forEach var="p" begin="${pagination.startPage }" end="${pagination.endPage }">
           			<li>
           				<c:choose>
           					<c:when test="${pagination.currentPage==p }">
           						<a class="page-link">${p}</a>
           					</c:when>
           					<c:otherwise>
           						
           						<c:if test="${empty param.memberNo &&empty like }">
	           						<a class="page-link" href="${pageURL}&cp=${p}">${p}</a>
           						</c:if>
           						<c:if test="${!empty param.memberNo && empty param.like }">
	           						<a class="page-link" href="${contextPath }/board/list2/my?memberNo=${loginMember.memberNo}&type=2&cp=${p}">${p}</a>
           						</c:if>
           						<c:if test="${!empty param.like }">
           						
	           						<a class="page-link" href="${contextPath }/board/list2/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=2&cp=${p}">${p}</a>
           						</c:if>
           						
           					</c:otherwise>

           				</c:choose>
           			
           			</li>
           		</c:forEach>
           		
           		
           		
           		<c:if test="${pagination.currentPage<pagination.maxPage }">
       			        <c:if test="${empty param.memberNo &&empty like }">
       			        
							<li><a class="page-link" href="${pageURL}&cp=${pagination.currentPage+1}">&gt;</a> </li>                		
       					</c:if>
       					<c:if test="${!empty param.memberNo&& empty param.like }">
							<li><a class="page-link" href="${contextPath }/board/list2/my?memberNo=${loginMember.memberNo}&type=2&cp=${pagination.currentPage+1}">&gt;</a> </li>                		
       			        
       					</c:if>
       					
       					<c:if test="${!empty param.like }">
							<li><a class="page-link" href="${contextPath }/board/list2/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=2&cp=${pagination.currentPage+1}">&gt;</a> </li>                		
           						
           				</c:if>
 					   
           		</c:if>	
           		
           		<c:if test="${pagination.endPage<pagination.maxPage }">
           			<c:if test="${empty param.memberNo &&empty like }">
						<li><a class="page-link" href="${next}">&gt;&gt;</a></li>              		
       				</c:if>
           		</c:if>	
           	
           	</ul>
           
           </div>
            
        
    </div>
    <div style="padding : 20px;"></div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
</body>
</html>