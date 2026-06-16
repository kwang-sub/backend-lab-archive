<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>billieJoe admin</title>
  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
    crossorigin="anonymous"></script>
  <link href="../dist/css/admin_sidebars.css" rel="stylesheet">

  <style>
    a {
      text-decoration: none;
      color: black;
    }

    hr {
      width: 70%;
    }

    .border-bottom {
      width: 70%;
    }
    h6{
      width: 110%;
    }

    #update-btn{
      margin-left: 20%;
      width: 25%;
    }

    .shadow-sm {
    box-shadow: none;
    }

    .b-example-divider{
        width: 1px;
    }

    th{
        text-align: center;
        border: 1px solid black;
    }

    td{
        text-align: center;
    }

	  .flex-shrink-0{
	
		display : inline-block;
		float :left;
		
		}
	
	 .containerH{
	    margin-left: 200px;
    	width: 900px;
	      }    
  </style>
</head>

<body>
  <main>
   <jsp:include page="../common/adminSidebar.jsp"></jsp:include>
      <div class="b-example-divider"></div>
      
    <div class="containerH py-5">
        <table class="table table-striped table-hover w-100">
          <thead>
            <tr>
              <th scope="col">글번호</th>
              <th scope="col">장소명</th>
              <th scope="col">가격</th>
              <th scope="col">작성자</th>
              <th scope="col">게시물 삭제</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${boardList}" var="board">
            
	            <tr>
	              <td>${board.placeNo}</td>
	              <td><a href="${contextPath }/admin/${board.placeNo}/detailView">${board.placeName}</a></td>
	              <td>${board.placeCharge}</td>
	              <td>${board.memberName}</td>
	              <td>
	              	<c:if test="${board.placeStatus == 'Y' }">
	              		<button type="button" class="btn btn-danger btn-sm" onclick="updateStatus(${board.placeNo}, 'N', this)">삭제</button>
	              	</c:if>
	              	<c:if test="${board.placeStatus == 'N' }">
	              		<button type="button" class="btn btn-primary btn-sm" onclick="updateStatus(${board.placeNo}, 'Y', this)">복구</button>
	              	</c:if>
	              
	              </td>
	            </tr>
             </c:forEach>
          </tbody>
        </table>

          <c:if test="${!empty param.sk }">
     		 <c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}"  />
		  </c:if>
	    	  <c:set var="prev" value="main?cp=${pagination.prevPage}${searchStr }" />
			  <c:set var="next" value="main?cp=${pagination.nextPage}${searchStr }" />
		  
	  
      <!-- 페이징 -->
      <nav aria-label="Page navigation" id="navigation">
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
									<li><a class="page-link" href="${pageURL}?cp=${p}${searchStr}">${p}</a></li>
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

	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		// 삭제버튼 클릭 시
		function updateStatus(placeNo, placeStatus, btn){
			$.ajax({
				url : "updateStatus",
				data : {"placeNo": placeNo, "placeStatus": placeStatus},
				type : "POST",
				success : function(result){

					if(result > 0){
						// 삭제 버튼이였을 경우 -> 복구로 변경
						if( $(btn).hasClass("btn-danger") ){
							$(btn).removeClass("btn-danger");
							$(btn).addClass("btn-primary");
							$(btn).text("복구");
						}
						
						// 복구 버튼이였을 경우 -> 삭제로 변경
						else{
							$(btn).removeClass("btn-primary");
							$(btn).addClass("btn-danger");
							$(btn).text("삭제");
						}
					}
				}
				
			});
		}
	
	</script>



</body>

</html>