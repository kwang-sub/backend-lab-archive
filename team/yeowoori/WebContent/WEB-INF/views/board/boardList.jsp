<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>여행게시판</title>
        
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
		.bg-img{
			background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
		}
		.category-btn1{
			 background-color: #A66129;
           	 color:white;
		}

    </style>
</head>
<body>


	
	
    <div class="container">
        
        		<c:if test="${empty param.memberNo }">
        			<c:if test="${!empty param.area }">
        			<c:set var="area" value="&area=${param.area}"/>
        			</c:if>
                    	<form class="bg-white category-select" action="#" method="GET">
            
				            <div class="btn-group category-sm-btn">
				                <button class="btn btn-li btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				                  현재 카테고리
				                </button>
				                <div class="dropdown-menu">
				                    <a href="/yeowoori/board/list?type=1${area}">전체</a><br>
				                    <a href="/yeowoori/board/list?type=1&category=1${area}">분위기맛집</a><br>
				                    <a href="/yeowoori/board/list?type=1&category=2${area}">산책코스</a><br>
				                    <a href="/yeowoori/board/list?type=1&category=3${area}">커플데이트</a><br>
				                    <a href="/yeowoori/board/list?type=1&category=4${area}">카페투어</a><br>
				                    <a href="/yeowoori/board/list?type=1&category=5${area}" >기타</a><br>
				                </div>
				              </div>
				              
				              
				        </form>
				        <c:set var="category" value="btn-dark" />
				        
				        
				        <c:if test="${empty param.category }">
				        <form class="category-btn" action="#" method="GET">
				            <div style="padding:10px;"></div>
				            <a class="btn category-selected " href="/yeowoori/board/list?type=1${area}" style="color:white;">전체</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=1${area}" style="color: #A66129;">분위기맛집</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=2${area}" style="color: #A66129;">산책코스</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=3${area}" style="color: #A66129;">커플데이트</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=4${area}" style="color: #A66129;">카페투어</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=5${area}" style="color: #A66129;">기타</a>
				            <div style="padding : 20px"></div>
				        </form>
				        </c:if>
				        <c:if test="${param.category ==1 }">
				        <form class="category-btn" action="#" method="GET">
				            <div style="padding:10px;"></div>
				            <a class="btn category-unselected " href="/yeowoori/board/list?type=1${area}" style="color: #A66129;" >전체</a>
				            <a class="btn category-selected" href="/yeowoori/board/list?type=1&category=1${area}" style="color:white;">분위기맛집</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=2${area}" style="color: #A66129;">산책코스</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=3${area}" style="color: #A66129;">커플데이트</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=4${area}" style="color: #A66129;">카페투어</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=5${area}" style="color: #A66129;">기타</a>
				            <div style="padding : 20px"></div>
				        </form>
				        </c:if>
				        <c:if test="${param.category ==2  }">
				        <form class="category-btn" action="#" method="GET">
				            <div style="padding:10px;"></div>
				            <a class="btn category-unselected " href="/yeowoori/board/list?type=1${area}" style="color: #A66129;">전체</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=1${area}" style="color: #A66129;">분위기맛집</a>
				            <a class="btn category-selected" href="/yeowoori/board/list?type=1&category=2${area}" style="color:white;">산책코스</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=3${area}" style="color: #A66129;">커플데이트</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=4${area}" style="color: #A66129;">카페투어</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=5${area}" style="color: #A66129;">기타</a>
				            <div style="padding : 20px"></div>
				        </form>
				        </c:if>
				        <c:if test="${param.category ==3 }">
				        <form class="category-btn" action="#" method="GET">
				            <div style="padding:10px;"></div>
				            <a class="btn category-unselected " href="/yeowoori/board/list?type=1${area}" style="color: #A66129;" >전체</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=1${area}" style="color: #A66129;">분위기맛집</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=2${area}" style="color: #A66129;">산책코스</a>
				            <a class="btn category-selected" href="/yeowoori/board/list?type=1&category=3${area}" style="color:white;">커플데이트</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=4${area}" style="color: #A66129;">카페투어</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=5${area}" style="color: #A66129;">기타</a>
				            <div style="padding : 20px"></div>
				        </form>
				        </c:if>
				        <c:if test="${param.category ==4 }">
				        <form class="category-btn" action="#" method="GET">
				            <div style="padding:10px;"></div>
				            <a class="btn category-unselected " href="/yeowoori/board/list?type=1${area}" style="color: #A66129;">전체</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=1${area}" style="color: #A66129;">분위기맛집</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=2${area}" style="color: #A66129;">산책코스</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=3${area}" style="color: #A66129;">커플데이트</a>
				            <a class="btn category-selected" href="/yeowoori/board/list?type=1&category=4${area}" style="color:white;">카페투어</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=5${area}" style="color: #A66129;">기타</a>
				            <div style="padding : 20px"></div>
				        </form>
				        </c:if>
				        <c:if test="${param.category ==5 }">
				        <form class="category-btn" action="#" method="GET">
				            <div style="padding:10px;"></div>
				            <a class="btn category-unselected " href="/yeowoori/board/list?type=1${area}" style="color: #A66129;">전체</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=1${area}" style="color: #A66129;">분위기맛집</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=2${area}" style="color: #A66129;">산책코스</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=3${area}" style="color: #A66129;">커플데이트</a>
				            <a class="btn category-unselected" href="/yeowoori/board/list?type=1&category=4${area}" style="color: #A66129;">카페투어</a>
				            <a class="btn category-selected" href="/yeowoori/board/list?type=1&category=5${area}" style="color:white;">기타</a>
				            <div style="padding : 20px"></div>
				        </form>
				        </c:if>
				       
				        
        		</c:if>
                    
       	<c:if test="${!empty loginMember && loginMember.memberGrade == 'G' }">
       	
        <div class="" style="height: 35px;">
            <a href="${contextPath}/boardwrite?cp=${param.cp}&type=${param.type}" >
    
                <button id="write-btn" class="rounded-circle">+</button>
            </a>

        </div>
       	</c:if>
        <div class="row main">
        
        <c:choose>
        	<c:when test="${empty boardList }">
        		<h1 style="text-align: center;">
        			작성된 글이 없습니다
        		</h1>
        	</c:when>
        
        	<c:otherwise>
        	
        		
        	
        		<c:forEach items="${boardList }" var="board">
        		
	                            
	                            
        		
        		
        			 <div class="col-sm-6 col-md-4 card-block">
	                <div class="card" style="width: 18rem; border: none;">
	                    <div class="card-head">
	                        <table class="userProple">
	                            <th style="padding-right: 5px;">
	                            
	                            
	                            
	                            <%-- 유저프로필 이상확인 필요!!!!!!!!!!!!!!!!! --%>
	                            <c:choose>
	                            	<c:when test="${!empty board.memberProfile }">
	                            	
	                                <img src="${contextPath}${board.memberProfile}" class=" rounded-circle" alt="">
	                            	</c:when>
	                            	
	                            	<c:otherwise>
	                            		<img src="${contextPath}/resources/img/user.png" class=" rounded-circle" alt="">
	                            	</c:otherwise>
	                            </c:choose>
	                            
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
			                    <div class="bg-img card-img-top" style="background-image: url(${contextPath}/resources/img/noimage.png);"></div>
			                    </a>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<c:if test="${empty param.area }">
	                    		<a href="view?no=${board.boardNo}&cp=${pagination.currentPage}&type=${pagination.boardType}">
	                    		</c:if>
	                    		<c:if test="${!empty param.area }">
	                    		<a href="view?no=${board.boardNo}&cp=${pagination.currentPage}&type=${pagination.boardType}&area=${param.area}">
	                    		</c:if>
	                    		
			                    <div class="bg-img card-img-top" style="background-image: url(${contextPath}/${board.filePath[0]}${board.fileName[0]});"></div>

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
	                        
	                        		<div
										style="text-overflow: ellipsis; white-space: no-wrap; overflow: hidden; height: 25px; width: 100%;">

										<c:if test="${fn:length(board.boardTitle)>=17}">
											<b>${fn:substring(board.boardTitle,0,39) }...</b>
										</c:if>
										<c:if test="${fn:length(board.boardTitle)<17}">
											<b>${board.board.boardTitle }</b>
										</c:if>
									</div>
									
									<div
										style="text-overflow: ellipsis; white-space: no-wrap; overflow: hidden; height: 50px; width: 100%;">

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
	           
        	
        	
        	</c:otherwise>
        </c:choose>

            
  		
            
        </div>
    </div>
    
    <div style="padding:20px;"></div>
           			<c:if test="${!empty param.area && empty param.category  }">
           				<c:set var = "areaCategory" value="&area=${param.area }" />
           			</c:if>
           			
           			<c:if test="${empty param.area && !empty param.category  }">
           				<c:set var = "areaCategory" value="&category=${param.category }" />
           			</c:if>
           			
 					<c:if test="${!empty param.area && !empty param.category }">
           				<c:set var = "areaCategory" value="&category=${param.category }&area=${param.area }" />
 					</c:if>  
            <c:set var = "pageURL" value="list?type=${pagination.boardType}${areaCategory}"/>
            
            
            
            <c:if test="${!empty param.memberNo && empty param.like }">
       			        
            	<c:set var = "prev" value="${contextPath }/board/list/my?memberNo=${loginMember.memberNo}&type=1&&cp=${pagination.prevPage}"/>
       		</c:if>
            <c:if test="${empty param.memberNo }">
           		 <c:set var = "prev" value="${pageURL }&&cp=${pagination.prevPage}"/>
       			        
       		</c:if>
       		<c:if test="${!empty param.like }">
            	<c:set var = "prev" value="${contextPath }/board/list/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=1&&cp=${pagination.prevPage}"/>
           	</c:if>
       		
       		
       		<c:if test="${!empty param.like }">
	            <c:set var = "next" value="${contextPath }/board/list/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=1&&cp=${pagination.nextPage}"/>
           	</c:if>
       		
            <c:if test="${!empty param.memberNo && empty param.like }">
       			        
	            <c:set var = "next" value="${contextPath }/board/list/my?memberNo=${loginMember.memberNo}&type=1&&cp=${pagination.nextPage}"/>
       		</c:if>
            <c:if test="${empty param.memberNo }">
	            <c:set var = "next" value="${pageURL }&&cp=${pagination.nextPage}"/>
       			        
       		</c:if>
            
            
           		
           			        			
           		
            
            <!-- 페이지 버튼 누를시 쿼리스트링 값 유지 되는법 확인하기  -->
            
           
           <div class="pagination-box row" style=";">
           	<ul class="pagination">
           		<c:if test="${pagination.currentPage>pagination.pageSize }">
						<li><a class="page-link" href="${prev }">&lt;&lt;</a></li>          		
 					 
           		</c:if>	
           		
           		<c:if test="${pagination.currentPage>2 }">
						
						<c:if test="${empty param.memberNo &&empty like }">
							<li><a class="page-link" href="${pageURL}&cp=${pagination.currentPage-1}">&lt;</a></li>
           						
           				</c:if>                 		
						<c:if test="${!empty param.memberNo&& empty param.like }">
							<li><a class="page-link" href="${contextPath }/board/list/my?memberNo=${loginMember.memberNo}&type=1&cp=${pagination.currentPage-1}">&lt;</a></li>
           						
           				</c:if>
           				<c:if test="${!empty param.like }">
							<li><a class="page-link" href="${contextPath }/board/list/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=1&cp=${pagination.currentPage-1}">&lt;</a></li>
           						
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
	           						<a class="page-link" href="${contextPath }/board/list/my?memberNo=${loginMember.memberNo}&type=1&cp=${p}">${p}</a>
           						</c:if>
           						<c:if test="${!empty param.like }">
           						
	           						<a class="page-link" href="${contextPath }/board/list/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=1&cp=${p}">${p}</a>
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
							<li><a class="page-link" href="${contextPath }/board/list/my?memberNo=${loginMember.memberNo}&type=1&cp=${pagination.currentPage+1}">&gt;</a> </li>                		
       			        
       					</c:if>
       					
       					<c:if test="${!empty param.like }">
							<li><a class="page-link" href="${contextPath }/board/list/like?like=${param.like}&memberNo=${loginMember.memberNo}&type=1&cp=${pagination.currentPage+1}">&gt;</a> </li>                		
           						
           				</c:if>
 					   
           		</c:if>	
           		<c:if test="${pagination.endPage<pagination.maxPage }">
           			<c:if test="${empty param.memberNo &&empty like }">
						<li><a class="page-link" href="${next}">&gt;&gt;</a></li>              		
       				</c:if>
           			
           		</c:if>	
           	
           	</ul>
           
           </div>
            
<div style="padding:20px;"></div>
</body>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</html>