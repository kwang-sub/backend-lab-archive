<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>채팅방</title>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="chatting-area">
        <div id="chat-header" class="row">
            <div class="col-sm-6" id="header-left">
                <a href="list"><i class="bi bi-arrow-left"></i></a>
                <img src="${contextPath}/${member.memberPic }" alt="" id ="prople"style="border-radius: 50%;">
                <strong id="name" >${member.memberName }</strong>
            </div>
            <div class="col-sm-6" id="chat-content">
                <table >
                    <tr>
                        <td rowspan="2">
                            <img src="${contextPath}/${chatPlace.atList[0].filePath }${chatPlace.atList[0].fileName}" alt="">
                        </td>
                        <td class="td">${chatPlace.placeName }</td>
                    </tr>
                    <tr>
                        <td class="td"><strong><fmt:formatNumber value="${chatPlace.placeCharge }" pattern="#,###" />원/시간</strong></td>
                    </tr>
                </table>

            </div>
        </div>
		<ul class="display-chatting">
            
			<c:forEach items="${list}" var="msg">
				<fmt:formatDate var="chatDate" value="${msg.chatMsgDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
				<c:if test="${msg.memberNo == loginMember.memberNo }">
					<li class="myChat">
						<span class="chatDate">${chatDate}</span>
						<p class="chat">${msg.chatMsgContent }</p>
					</li>
				</c:if>
				
				<c:if test="${msg.memberNo != loginMember.memberNo }">
					<li>
						<b>${msg.memberName }</b>	<br>
						<p class="chat">${msg.chatMsgContent }</p>
						<span class="chatDate">${chatDate}</span>
					</li>
				</c:if>
			</c:forEach>
		</ul>	
	</div>
	<div class="input-area">
		<textarea id="inputChatting" rows="3"></textarea>
		<button id="send" class=""><i class="bi bi-cursor"></i></button>
	</div><br><br>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->
	
	<!-- 
		WebSocket : 브라우저와 웹서버간의 전이중 통신을 지원하는 프로토콜  
		(websocket을 위한 객체는 http를 이용해 생성되지만 생성 후 동작은 http와 별로도 진행된다)
		전이중(full duplex) 통신 : 두 단말기가 데이터를 송수신 하기 위해 각각 독립된 회선을 사용하는 것
		
		WebSocket을 사용하기 위한 객체를 Javascript가 기본적으로 제공하고 있지만 웹소켓을 제공하지 않는 브라우저도 있다.
		->sockjs를 이용하면 문제가 해결된다
		
		SockJS : 브라우저가 WebScoket을 제공하지 않을 때 비슷한 다른 객체를 찾아서 웹소켓 통신이 가능하도록 하는 Javascript 라이브러리	
	-->
	<!-- https://github.com/sockjs/sockjs-client -->
	<script>
		// -------------------------------------------- WebSocket ----------------------------------------------
	
		//let chattingSock; // SockJS를 이용한 WebSocket 객체 저장 변수 선언
	
		// 로그인이 되어 있을 경우에만
		// /chat 이라는 요청 주소로 통신할 수 있는  WebSocket 객체 생성
		const chattingSock = new SockJS("${contextPath}/chat");
		/* 
			생성된 SockJS 객체는 자바스크립트 (브라우저에서 동작 == 클라이언트) 
			/chat 주소로 오는 웹소켓 요청을 받아들이는 핸들러는 자바(서버에서 동작 == 서버)
		*/
		const memberNo = "${loginMember.memberNo}";
		const memberName = "${loginMember.memberName}";
		const chatRoomNo = "${chatRoom.chatRoomNo}";
		
		/* $("#inputChatting").keyup(function(e){
			if(e.keyCode == 13 ){
				const chat = $("#inputChatting").val(); // 입력한 채팅 내용
								
								if(chat.trim().length == 0){   //채팅 입력이 없을경우
									alert("채팅을 입력해주세요.");
								}else{	//채팅이 입력되었을 경우 자바스크립트 객체 obj를 만들어 로그인한 회원의 정보, 채티방 번호, 채팅 내용을 삽입
									var obj = { "memberNo" : memberNo,  
															"memberName" : memberName,
															"chat" : chat,
															"chatRoomNo" : chatRoomNo};
								
									//JSON : 자바스크립트 객체 표기법 , 자바스크립트 객체 모양의 문자열
									
									// JSON.stringify() : 자바스크립트 객체를 JSON 문자열로 변환
									chattingSock.send(JSON.stringify(obj));
									
									$("#inputChatting").val("");
								}
			}
		}); */
		$(".display-chatting").scrollTop($(".display-chatting")[0].scrollHeight);
		$("#send").on("click", function(){
				const chat = $("#inputChatting").val(); // 입력한 채팅 내용
				
				if(chat.trim().length == 0){   //채팅 입력이 없을경우
					alert("채팅을 입력해주세요.");
				}else{	//채팅이 입력되었을 경우 자바스크립트 객체 obj를 만들어 로그인한 회원의 정보, 채티방 번호, 채팅 내용을 삽입
					var obj = { "memberNo" : memberNo,  
											"memberName" : memberName,
											"chat" : chat,
											"chatRoomNo" : chatRoomNo};
				
					//JSON : 자바스크립트 객체 표기법 , 자바스크립트 객체 모양의 문자열
					
					// JSON.stringify() : 자바스크립트 객체를 JSON 문자열로 변환
					/* 웹소켓 통신 객체를 이용해 /chat 주소로 obj를 JSON으로 변환한 문자열을 전송 */
					chattingSock.send(JSON.stringify(obj));
					
					$("#inputChatting").val("");
				}
		});
		
		
		// WebSocket 객체 chattingSock이 서버로 부터 메세지를 통지 받으면 자동으로 실행될 콜백 함수
		chattingSock.onmessage = function(event){
			// 메소드를 통해 전달받은 객체값을 JSON객체로 변환해서 obj 변수에 저장.
			const obj = JSON.parse(event.data);
			console.log(obj);
			
			
			const li = $("<li>");
			const p = $("<p class='chat'>");
			const span = $("<span class='chatDate'>");
			span.html(obj.createDate);
			
			const chat = obj.chat.replace(/\n/g, "<br>");	
			p.html(chat);
			
			
			if(obj.memberNo == memberNo){
				li.addClass("myChat");
				li.append(span);								
				li.append(p);
			}else{
				li.html("<b>" + obj.memberName + "</b><br>");
				li.append(p);
				li.append(span);								
			}
			
			$(".display-chatting").append(li);
			
			// 채팅 입력 시 스크롤을 가장 아래로 내리기
			$(".display-chatting").scrollTop($(".display-chatting")[0].scrollHeight);

		}	
	</script>
	
     
</body>
</html>