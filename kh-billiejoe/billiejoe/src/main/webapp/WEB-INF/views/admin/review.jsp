<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>billieJoe admin</title>
  
  <style type="text/css">
  	button {
		height: 35px;	
	}
	
	table{
		text-align: center;
	}
	
	#rc{
		width: 200px;
		height: 25px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	
	#rc > a{
		text-decoration: none;
		color: black;
	}

	#ct{
		padding-left: 50px;
	
	}
	
	
	
  </style>
</head>

<body>
  <main>
  
  <jsp:include page="../common/adminSidebar.jsp"></jsp:include>
    
    <div class="b-example-divider"></div>
    <div class="container py-5 overflow-auto" >
      <h2>후기 관리</h2>
      <br>
        <table class="table table-striped table-hover w-100">
          <thead>
            <tr>
              <th scope="col">후기번호</th>
              <th scope="col">후기 내용</th>
              <th scope="col">별점</th>
              <th scope="col">작성자</th>
              <th scope="col">글삭제</th>
            </tr>
          </thead>
          <tbody>
          
          <c:forEach items="${adminReviewList}" var="adminReviewList">
          
            <tr>
              <td width="20%">${adminReviewList.reviewNo }</td>
              <td width="40%" id=ct>
    	          <div id="rc" align="left">
	    	          <a href="${adminReviewList.reviewNo}?cp=${pagination.currentPage}">
	    	          	${adminReviewList.reviewContent }
	    	          </a>
	    	  	  </div>        
	    	  </td>
              <td width="10%">${adminReviewList.reviewAvg }점</td>
              <td width="20%">${adminReviewList.memberName }</td>
              <td width="10%">
                <button type="button" class="btn btn-danger del-btn" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  삭제
                </button>
                  <input type="hidden" class="report-no" value="${adminReviewList.reviewNo}">
              </td>
            </tr>

          </c:forEach>
           
          </tbody>
        </table>
        
       <c:set var="prev" value="report?cp=${pagination.prevPage}" />
	  <c:set var="next" value="report?cp=${pagination.nextPage}" />
       <!-- 페이징 -->
      <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
          <li class="page-item">
            <a class="page-link" href="${prev}" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <%-- 페이지 목록 --%>
					<c:forEach var="p" begin="${pagination.startPage}" end="${pagination.endPage}">
						
							<c:choose>
								<c:when test="${p == pagination.currentPage }">
									<li class="page-item active"><a class="page-link">${p}</a></li>
								</c:when>
								
								<c:otherwise>
									<li><a class="page-link" href="${pageURL}?cp=${p}">${p}</a></li>
								</c:otherwise>
							</c:choose>						
					</c:forEach>
          <li class="page-item">
            <a class="page-link" href="${next }" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
    
  </main>
     <!-- Modal -->
     <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content" >
          <div class="modal-header">
            <p >정말 삭제 하시겠습니까?</p>
          </div>
          <form action="deleteReview" method="POST">
              <div class="modal-footer">
                  <button type="submit" class="btn btn-primary modal-btn">네</button>
                  <button type="button" class="btn btn-primary modal-btn" data-bs-dismiss="modal">아니오</button>
                <input type="hidden" name="reviewNo" id="modal-report-no">
              </div>
          </form>
        </div>
      </div>
    </div>
  
  

  <script>
    $(document).ready(function(){
        $(".del-btn").on("click",function(){
            console.log($(event.target))
            var number = $(event.target).children().val();
            var number2 = $(event.target).children().last().val();
            $("#modal-report-no").val(number);
        })
    })
</script>
</body>

</html>