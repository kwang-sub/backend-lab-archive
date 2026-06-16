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
  
</head>

<body>
  <main>
    <jsp:include page="../common/adminSidebar.jsp"></jsp:include>
  
    
    <div class="b-example-divider"></div>
    <div class="container py-5">
      <h2>탈퇴 회원</h2>
      <form method="get" action="unRegMember">
        <div class="input-group mb-3 w-50">
          <select name="sk" class="form-select" id="searchUserCond" >
            <option value="name">이름</option>
            <option value="id">아이디</option>
          </select>
          <input type="text" class="form-control" name="sv" placeholder="이름 혹은 아이디로 검색하세요">
          <button class="btn btn-outline-secondary" type="submit" id="searchUser" >검색</button>
        </div>
        <table class="table table-striped table-hover w-100">
          <thead>
            <tr>
              
              <th scope="col">회원번호</th>
              <th scope="col">이름</th>
              <th scope="col">아이디</th>
              <th scope="col">연락처</th>
              <th scope="col">가입일자</th>
              <th scope="col">탈퇴일자</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${memberList }" var="member">
          
            <tr>
              <td>${member.memberNo }</td>
              <td>${member.memberName }</td>
              <td>${member.memberEmail }</td>
              <td>${member.memberPhone }</td>
              <fmt:formatDate var="regDate" value="${member.regDate}" pattern="yyyy.MM.dd"/>
              <td>${regDate }</td>
              <fmt:formatDate var="unRegDate" value="${member.unRegDate}" pattern="yyyy.MM.dd"/>
              <td>${unRegDate}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </form>
      
	  <c:if test="${!empty param.sk }">
     	 <c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}"  />
	  </c:if>
      <c:set var="prev" value="unRegMember?cp=${pagination.prevPage}${searchStr }" />
	  <c:set var="next" value="unRegMember?cp=${pagination.nextPage}${searchStr }" />
	  
	  
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
    
  

  <script>
    $(document).ready(function(){
        var searchKey = "${param.sk}"; 
		// 파라미터 중 sk가 있을 경우   ex)  "abc"
		// 파라미터 중 sk가 없을 경우   ex)  ""
		var searchValue = "${param.sv}";
		
		// 검색창 select의 option을 반복 접근
		$("select[name=sk] > option").each(function(index, item){
			// index : 현재 접근중인 요소의 인덱스
			// item : 현재 접근중인 요소
						// content            content
			if( $(item).val() == searchKey  ){
				$(item).prop("selected", true);
			}
		});		
		
		// 검색어 입력창에 searcValue 값 출력
		$("input[name=sv]").val(searchValue);
    })
</script>
</body>

</html>