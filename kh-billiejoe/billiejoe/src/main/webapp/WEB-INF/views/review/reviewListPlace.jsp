<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

  <div class="container py-5">
    <div class="row ">
   		<div class="row">
	   		<div class="col-2">
		        <c:choose>
			        <c:when test="${add.reviewCount>0}">
		        		<h4>총 후기 : ${add.reviewCount}개</h4>
		    	    </c:when>
	    	    
		    	    <c:otherwise>
		        		<h4>총 후기 : 0개</h4>
		    	    </c:otherwise>
		        </c:choose>
	        </div>
      
   		<div class="col-3">
	        <h4>
	        	평점 : 
			              <c:choose>
				              <c:when test="${add.reviewAvg==1}">
					              <i id="red">★</i>
					              <i>★</i>
					              <i>★</i>
					              <i>★</i>
					              <i>★</i>
				              </c:when>
				              <c:when test="${add.reviewAvg==2}">
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i>★</i>
					              <i>★</i>
					              <i>★</i>
				              </c:when>
				              <c:when test="${add.reviewAvg==3}">
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i>★</i>
					              <i>★</i>
				              </c:when>
				              <c:when test="${add.reviewAvg==4}">
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i>★</i>
				              </c:when>
				              <c:when test="${add.reviewAvg==5}">
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i id="red">★</i>
					              <i id="red">★</i>
				              </c:when>
				              <c:otherwise>
					              <i></i>
				              </c:otherwise>
			              </c:choose>
	                      <i>&nbsp;&nbsp;${add.reviewAvg}점</i>
	      	</h4>
      	</div>
    </div>
    
    <hr>
	<c:choose>
	
		<c:when test="${empty reviewListPlace}">
			<div>후기글이 존재하지 않습니다.</div>
		</c:when>
      
		<c:otherwise>				
			<c:forEach items="${reviewListPlace}" var="review">
	
		    <div class="row viewWrapper">
		       
		    	<div class="col-3 profile content text-center">
		        	<img src="${contextPath}/${review.memberPic}">
		    	</div>
		
			<div class="col content">
			
		        
		        <%-- 작성일 --%>
				<div class="reviewDate">
					작성일 : &nbsp;
					<fmt:formatDate var="reviewDate" value="${review.reviewDate}"  pattern="yyyy-MM-dd"/>                          
					<fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>                          
				
					<c:choose>
		
		                <%-- 글 작성일이 오늘이 아닐 경우 --%>
		                <c:when test="${reviewDate != today}">
		                	${reviewDate}
		                </c:when>
		                
		                <%-- 글 작성일이 오늘일 경우 --%>
		                <c:otherwise>
		                    <fmt:formatDate value="${review.reviewDate}"  pattern="HH:mm"/>                          
		                </c:otherwise>
		                
			        </c:choose>
			     </div>
			
		        <div class="memName">
		          <h3>${review.memberName}</h3>
		        </div>
		
		          <div class="rate">
		              <p class="star">
		              <c:choose>
			              <c:when test="${review.reviewRate==1}">
				              <i id="gold">★</i>
				              <i>★</i>
				              <i>★</i>
				              <i>★</i>
				              <i>★</i>
			              </c:when>
			              <c:when test="${review.reviewRate==2}">
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i>★</i>
				              <i>★</i>
				              <i>★</i>
			              </c:when>
			              <c:when test="${review.reviewRate==3}">
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i>★</i>
				              <i>★</i>
			              </c:when>
			              <c:when test="${review.reviewRate==4}">
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i>★</i>
			              </c:when>
			              <c:otherwise>
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i id="gold">★</i>
				              <i id="gold">★</i>
			              </c:otherwise>
		              </c:choose>
		              <i>&nbsp;&nbsp;${review.reviewRate}점</i>
		          </div>
		          
		          <hr>
		            
			          <div class="placeContent">
						${review.reviewContent}
			          </div>
			          
			    <c:choose>
			    	<c:when test="${loginMember.memberNo==place.memberNo}">
			          <form method="POST" action="#modal-container-1">
						  <div align="right" >
						  	<a class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#exampleModal" href="#exampleModal" onclick="fnReviewNo(${review.reviewNo});">신고</a>
						  </div>
					  </form>
					</c:when>
				</c:choose>
		      </div>
	      </div>
	      
	      <div>
			    <hr>
	      </div>
			    
			</c:forEach>
		</c:otherwise>				
	</c:choose>	
</div>	  
</div>	  
<%---------------------- Pagination start----------------------%>
<c:set var="pageURL" value="view" />
<c:set var="prev" value="${pageURL}?cp=${pagination.prevPage}" />
<c:set var="next" value="${pageURL}?cp=${pagination.nextPage}" />
<nav>
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${pagination.currentPage > 2 }">
				<li class="page-item">
					<a class="page-link" href="${pageURL}?cp=${pagination.currentPage - 1}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled">
					<a class="page-link" href="#" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="p" begin="${pagination.startPage}" end="${pagination.endPage}">
			<c:choose>
				<c:when test="${p == pagination.currentPage }">
					<li class="page-item active"><a class="page-link" href="#">${p}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${pageURL}?cp=${p}">${p}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${pagination.currentPage < pagination.maxPage }">
				<li class="page-item">
					<a class="page-link" href="${pageURL}?cp=${pagination.currentPage + 1}${searchStr}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled">
					<a class="page-link" href="#" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
<%---------------------- Pagination end----------------------%>
        	<%---------------------- 신고하기 모달----------------------%>
				<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			        <div class="modal-dialog" >
			            <div class="modal-content">
		                    <form class="report" method="POST" action="report">
		                        <div class="modal-body">
				                	<h3>신고 사유</h3>
		                        	<input class="form-control" maxlength="30" type="text" name="reportContent" placeholder="30자내로 내용을 입력하세요.(ex. 욕설, 허위)">
		                        </div>        
				                <div class="modal-footer">
							          	<input type="hidden" name="reviewNo" id="reviewNo" >
				                        <button class="btn btn-primary modal-btn" type="submit" id=curse >신고하기</button>
					                    <button type="button" class="btn btn-primary modal-btn" data-bs-dismiss="modal">취소</button>
				                </div>
		                    </form>
			            </div>
			        </div>
			    </div>

    <script>
		function fnReviewNo(rid){
			$('#reviewNo').val(rid)
			console.log(rid);
		}
    </script>
			    