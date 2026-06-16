<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	 <style>
        #type{
            text-align: center;
        }
        .pagination {
			margin-left: auto; 
			margin-right: auto;
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
          
    </style>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	 <div class="container">
        <div class="row">
          <div class="col-sm-1">
          </div>
          <div class="col-sm-10">
              <div id="type">
                  <h2>공지사항</h2>
              </div>
              
              	<c:if test="${loginMember.memberGrade == 'A' }">
	                 <div class="" style="height: 35px;">
			            <a href="${contextPath}/boardwrite?cp=${param.cp}&type=${param.type}" >
			    
			                <button id="write-btn" class="rounded-circle">+</button>
			            </a>
			
			        </div>
              	
              	</c:if>
		        
		        
				 <c:forEach items="${boardList }" var="board">
						<div>
			                  <hr>
						        <h5>[공 지] ${board.boardTitle }</h5> <br>
						        ${board.boardContent }<br><br>
						     <fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd"/>
			              </div>
				</c:forEach> 
				
				
				<div>
					 <c:set var = "pageURL" value="list?type=${pagination.boardType}"/>
            <c:set var = "prev" value="${pageURL }&&cp=${pagination.prevPage}"/>
            <c:set var = "next" value="${pageURL }&&cp=${pagination.nextPage}"/>
            
            
            
            <!-- 페이지 버튼 누를시 쿼리스트링 값 유지 되는법 확인하기  -->
            
           
           <div class="pagination-box row" style=";">
           	<ul class="pagination">
           		<c:if test="${pagination.currentPage>pagination.pageSize }">
					<li><a class="page-link" href="${prev }">&lt;&lt;</a></li>          		
           		</c:if>	
           		<c:if test="${pagination.currentPage>=2 }">
					<li><a class="page-link" href="/yeowoori/board/list4?type=4&cp=${pagination.currentPage-1}">&lt;</a></li>                 		
           		</c:if>	
           		
           		<c:forEach var="p" begin="${pagination.startPage }" end="${pagination.endPage }">
           			<li>
	           			<a class="page-link" href="/yeowoori/board/list4?type=4&cp=${p}">${p}</a>
           			</li>
           		</c:forEach>
           		
           		<c:if test="${pagination.currentPage<pagination.maxPage }">
					<li><a class="page-link" href="/yeowoori/board/list4?type=4&cp=${pagination.currentPage+1}">&gt;</a> </li>                		
           		</c:if>	
           		<c:if test="${pagination.endPage<pagination.maxPage }">
					<li><a class="page-link" href="${next}">&gt;&gt;</a></li>              		
           		</c:if>	
           	
           	</ul>
           
           </div>
				
				</div>
              
          </div>
          	
          <div class="col-sm-1">
          </div>
        </div>
      </div>
</body>
</html>