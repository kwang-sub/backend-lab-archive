<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임 중복 검사</title>
</head>
<style>
	body>*{
		margin-left: 75px;
	}
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<body>
	<h4 class="mt-3">닉네임 중복 검사</h4>
	<br>
	
	<form action="${contextPath }/member/nickDupCheck" id="nickCheckForm" method="post">
		<input type="text" id="nick" name="nickname" >
		<input type="submit" value="중복확인">
	</form>
	<br>
	
	<!-- 사용 가능 여부 메세지 출력 -->
	<span>
	
	<%-- 중복 검사를 수행한 경우 --%>
	<c:if test="${!empty result }">
		<c:choose>
			<c:when test="${result == 0 }">사용 가능한 닉네임입니다.</c:when>
			<c:otherwise>이미 사용중인 닉네임입니다.</c:otherwise>
		</c:choose>
	</c:if>
	</span>
	<br>
	<br>
	
	<div>
		<input type="button" id="cancel" value="취소" onclick="window.close();">
		<input type="button" id="confirmNick" value="확인" onclick="confirmNick();">
	</div>
	
	<script>
		let nick; // 입력된 닉네임 저장하기 위한 변수	
		const result = "${result}"; 
		
		$(function(){
			
			if(result == ""){ 
				nick = opener.document.signUpForm.nickName.value;
				
			} else{
				nick = "${nickname}";
			}
			
			$("#nick").val(nick);
		});
		
		// 확인버튼 클릭 시 동작
		function confirmNick(){
			
			const regExp = /^[가-힣]{2,15}$/;
			
			if (!regExp.test( $("#nick").val())) {
				alert("닉네임 형식이 유효하지 않습니다.");
				/* swal({
					"icon" : "warning",
					"title" : "닉네임이 유효하지 않습니다.",
					"text" : "한글 2~15자리이내로 작성해주세요."
				}); */
				return false;
			}
			
			
			if(result === "0"){ 
				opener.document.signUpForm.nickDup.value = true;
				
			}else{
				opener.document.signUpForm.nickDup.value = false;	
			}
			
			opener.document.signUpForm.nickName.value = $("#nick").val();
			
			if(opener != null){
				self.close();
			}
			
			
		}
		$("#nickCheckForm").submit(function(){
			
		    const regExp = /^[가-힣]{2,15}$/;

		    const inputnick = $("#nick").val().trim();

		    if(!regExp.test(inputnick)){
		    	alert("유효하지 않은 닉네임 형식입니다.");
		    	return false;
		    }
		});
		
		
	</script>
	
	<script src="${contextPath}/resources/js/member.js"></script>
	<c:remove var="id"/>
	<c:remove var="result"/>
</body>
</html>