<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>채팅방</title>
<style>
	
    </style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<h2>채팅목록</h2>
		</div>
		<br>
		<br>
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
			<c:forEach items="${chatList }" var="chat">
			<c:set var="today"><fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" /></c:set>
			<c:set var="lastMsg"><fmt:formatDate value="${chat.chatMsgDate }" pattern="yyyy-MM-dd" /></c:set>
			<c:choose>
			 	<c:when test="${today==lastMsg}">
					<c:set var="lastMsg"><fmt:formatDate value="${chat.chatMsgDate }" pattern="a hh:mm" /></c:set>
				</c:when> 
				<c:otherwise>
					<c:set var="lastMsg"><fmt:formatDate value="${chat.chatMsgDate }" pattern="M월 d일" /></c:set>
				</c:otherwise>
			</c:choose>
			
			
				<div class="chat-box">

					<button type="button" class="btn del-btn btn-close "
						data-bs-toggle="modal" data-bs-target="#exampleModal">

						<input type="hidden" class="chat-no" value="${chat.chatRoomNo }">
					</button>
					<a href="${contextPath}/chat/room?placeMemberNo=${chat.placeMemberNo}&placeNo=${chat.chatPlaceNo}&joinMemberNo=${chat.member.memberNo}">
						<table>
							<tr>
								<td rowspan="2" id="prople-td">
									<img class="prople"src="${contextPath}/${chat.member.memberPic }"alt="">
								</td>
								<td class="chatList-content">${chat.member.memberName }</td>
								<td>${lastMsg}</td>
								<td rowspan="2">
									<img class="area" src="${contextPath }/${chat.chatFilePath }${chat.chatFileName}"alt="">
								</td>
							</tr>
							<tr>
								<td class="chatList-content">${chat.chatMsg }</td>
							</tr>
						</table>
						<br>
					</a>
				</div>
				<br>
			
			</c:forEach>

			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>



	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<p>
						해당 채팅방을 <br>삭제하시겠습니까?
					</p>
				</div>
				<form action="delete" method="POST">
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary modal-btn">네</button>
						<button type="button" class="btn btn-primary modal-btn"
							data-bs-dismiss="modal">아니오</button>
						<input type="hidden" name="chatRoomNo" id="modal-chat-no">
					</div>
				</form>
			</div>
		</div>
	</div>

	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../../../resources/js/chatViewJs.jsp"></jsp:include> 

</body>
</html>