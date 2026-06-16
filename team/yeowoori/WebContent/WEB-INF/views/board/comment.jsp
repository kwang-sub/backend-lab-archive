<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath }"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>comment</title>
</head>
<body>
<style>
     
        .user-profile{
            width: 20px; height: 20px;
            border: 1px solid gainsboro;
            border-radius: 100px;
            background-repeat: no-repeat;
            background-size: cover;
            display: inline-block;
            vertical-align:middle;
       }
        .user-profile-big{
            width: 40px; height: 40px;
            border: 1px solid gainsboro;
            border-radius: 100px;
            background-repeat: no-repeat;
            background-size: cover;
            display: inline-block;
            vertical-align:middle;
            margin-right: 20px;
            margin-bottom: 30px;
       }
       .user-name{
           width:fit-content;
           display: inline-block;
           font-size: 14px;
           margin-left: 0px;
           font-weight: 500;
       }
       .time{
           width:fit-content;
           display: inline-block;
           font-size: 12px;
           margin-left: 30px;
           font-weight: 300;
       }
      
       .comments{
        width:fit-content;
           display: inline-block;
           font-size: 12px;
           margin-right: 10px;
           font-weight: 400;
           color: grey;
       }
       .report{
            color: grey;
           font-size: 12px;
           margin-left: 10px;
           font-weight: 300;
           display: inline-block;
       }
       .text-container{
           display: inline-block;
           width: 80%; height: 35px;
           border : 1px solid rgb(192, 192, 192);
           border-radius: 5px;

       }
       .input-comment{
           width: 85%; height: 32px;
           border: 0px;
           border-radius: 5px;
       }
       .btn-submit{
           background-color: white;
           border: 0px;
           padding: 0px;
           height: 5px;
           color: grey;
           font-weight: 500;
           float: right;
           margin-right: 10px;
           line-height: 2.3;
       }
       .btn-dh{
       		background-color : #eee;
       		border : 0px;
       		padding-left: 5px; padding-right:5px;
       		padding-top:3px; padding-bottom:3px;
       		font-size : 12px;
       		margin-right:10px;
       }
		.comment-content{
		display: inline-block; margin-left: 10px; font-size: 14px;
		}
		.CommentUpdateContent {
		   width: 80%; height: 32px;
           border: 1px solid grey;
           border-radius: 5px;
           resize:none;
           display:inline-block;
           margin:0px;
		}
</style>
    
    <div class="container">
      
   
      	<hr>
      	<div style="padding: 20px;"></div>
        <h6>댓글 ${board.commentCount }</h6>
        <div style="padding: 5px;"></div>
         
        <c:if test="${!empty loginMember }">
        <div class="user-profile-big" style="background-image: url(${contextPath}/${loginMember.memberProfile});"></div>
        <div class="text-container">
            <input type="text" class="input-comment" id="commentContent" name="commentContent" placeholder="댓글을 입력해주세요.">
            <button type="button" class="btn-submit" id="addComment" onclick="addComment();">등록</button>
        </div>
        </c:if>
        
     
        <div id="comment-list-area">
        <c:if test="${!empty commentList}">
        <c:forEach items="${commentList}" var="comment">
        <c:set var="commentNo" value="${comment.commentNo }"/>
	        <div style="padding: 10px;"></div>
	        <div class="user-profile-big" style="background-image: url(${contextPath}/${comment.memberProfile});"></div>
	        <div style="display: inline-block;">
	            <div class="user-name">${comment.memberNickname }</div>
	            <div class="comment-content">${comment.commentContent }</div>
				
				<c:if test="${loginMember.memberNo == comment.memberNo}">
				<div class="ml-2" style="position:absolute; right:100px;">
		             <button type="button" class="btn btn-dh" onclick="showUpdateComment(${comment.commentNo}, this)">수정</button>
		             <button type="button" class="btn btn-dh" onclick="deleteComment(${comment.commentNo})">삭제</button>
	           	</div>
				</c:if>
				
				
	            <div>
	                <div class="time" style="margin-left: 0px; margin-right: 20px;">${comment.commentDate}</div>
	                <a href="${contextPath }/board2/like/comment?commentNo=${comment.commentNo}&memberNo=${loginMember.memberNo}" >
	                    <img src="https://drive.google.com/uc?id=1e719tW6BVTrSPiZQIxJZ8LUWWuNsx0Lc" height="15px">
	                    <div class="comments">좋아요</div>
	                </a>
	                
	                <c:if test="${loginMember.memberNo != comment.memberNo }">
	                 <a href="${contextPath }/report/comment?commentNo=${comment.commentNo}&memberNo=${loginMember.memberNo}" class="report">신고</a>
	                </c:if>
	               
	
	            </div>
	            
	
	        </div>
	        

		
	    </c:forEach>
        
     	</c:if>
        </div>

    
    <script>
	
    	const loginMemberNo = "${loginMember.memberNo}";
    	const boardNo = ${board.boardNo};
    	let beforCommentRow;
    	
    	//댓글 등록
    	function addComment(){
    		const commentContent = $("#commentContent").val();
    		if(commentContent.trim() =="") {
    			swal("댓글 내용이 없습니다.");
    		}else{
    			$.ajax({
    				url : "${contextPath}/comment/insertComment",
    				type : "POST",
    				data : {"memberNo" : loginMemberNo,
    						"boardNo" : boardNo,
    						"commentContent" : commentContent },
    				success : function(result){
    					if(result>0){
    						swal({
        						"icon" : "success",
        						"title" : "댓글 등록 성공"
        					})
        					$("#commentContent").val("");
    						selectCommentList();
    					}
    					
    				},
    				error : function(){
    					console.log("댓글 삽입 실패");
    				}
    			})
    		}
    	}
    	
    	//댓글 조회
    	function selectCommentList(){
 			
			$.ajax({
				url : "${contextPath}/comment/list",
				data : {"boardNo" : boardNo},
				type : "POST",
				dataType : "JSON", 
				success : function(commentList){
					
			        $("#comment-list-area").html("");
			         
			        $.each(commentList, function(index, item){
					
			        	var div1 = $("<div>").css("padding", "10px");
			        	var div2 = $("<div>").addClass("user-profile-big").css("background-image", "url(${contextPath}/"+item.memberProfile+")");
			        	
			        	var div3 = $("<div>").css("display", "inline-block");
			        	
			        	var div31 = $("<div>").addClass("user-name").text(item.memberNickname);
			        	var div32 = $("<div>").addClass("comment-content").html(item.commentContent);
			        	var div33 = $("<div>").addClass("ml-2").css("position", "absolute").css("right", "100px");
			        	
			        	
			        	if(item.memberNo == loginMemberNo){
			        		   
				               var showUpdate = $("<button>").addClass("btn btn-dh").text("수정").attr("onclick", "showUpdateComment("+item.commentNo+", this)");
				               var deleteComment = $("<button>").addClass("btn btn-dh").text("삭제").attr("onclick", "deleteComment("+item.commentNo+")");
				               
				               div33.append(showUpdate).append(deleteComment);
				        }

			        	div3.append(div31).append(div32).append(div33);
			        	
			        	var div4 = $("<div>");
			        	
			        	var div41 = $("<div>").addClass("time").css("margin-left", "0px").css("margin-right", "20px").text(item.commentDate);
			        	
			        	var a42 = $("<a>").prop("href", "${contextPath }/board2/like/comment?commentNo="+item.commentNo+"&memberNo=${loginMember.memberNo}");
			        	var img421 = $("<img>").attr("src", "https://drive.google.com/uc?id=1e719tW6BVTrSPiZQIxJZ8LUWWuNsx0Lc").css("height", "15px");
			        	var div422 = $("<div>").addClass("comments").text("좋아요");
			        	a42.append(img421).append(div422);
			        	
			        	div4.append(div41).append(a42);
			        	
			        	if (item.memberNo != loginMemberNo){
			        		var a43 = $("<a>").prop("href", "${contextPath }/report/comment?commentNo="+item.commentNo+"&memberNo=${loginMember.memberNo}").addClass("report").text("신고");
			        		div4.append(a43);
			        	
			        	}
			        	
			        	div3.append(div4);
			        	
			            $("#comment-list-area").append(div1).append(div2).append(div3);
			         }); 
					
				},
				error : function(){
					console.log("댓글 목록 조회 실패")
				}
			});
		}
    	
    	//댓글 수정 열리기
    	function showUpdateComment(commentNo, el){
    		
    		if($(".replyUpdateContent").length > 0){
    			$(".replyUpdateContent").eq(0).parent().html(beforeReplyRow);
    		}
    		
    		beforeReplyRow = $(el).parent().parent().html();
    		
    		var beforeContent = $(el).parent().prev().html();
    		
    		beforeContent = beforeContent.replace(/&amp;/g, "&");	
    		beforeContent = beforeContent.replace(/&lt;/g, "<");	
    		beforeContent = beforeContent.replace(/&gt;/g, ">");	
    		beforeContent = beforeContent.replace(/&quot;/g, "\"");	
    		beforeContent = beforeContent.replace(/<br>/g, "\n");	
    		
    		var textarea = $("<textarea>").addClass("CommentUpdateContent").val(beforeContent);
    		$(el).parent().parent().before(textarea);
    		
    		
    		var updateBtn = $("<button>").addClass("btn btn-dh").text("댓글 수정").attr("onclick", "updateComment(" + commentNo + ", this)");
    		var cancelBtn = $("<button>").addClass("btn btn-dh").text("취소").attr("onclick", "updateCancel(this)");
    		var div = $("<div>").css("display", "inline-block").addClass("ml-2");
    		div.append(updateBtn).append(cancelBtn);
    		
    		$(el).parent().parent().before(div);		
    		$(el).parent().parent().remove();
    	
    	}
    	
    	function updateCancel(el){
    		$(el).parent().parent().html( beforeReplyRow );
    	}
    	
    	function updateComment(commentNo, el){
    		const commentContent = $(el).parent().prev().val();
    		$.ajax({
    			url : "${contextPath}/comment/updateComment",
    			type :"POST",
    			data : { "commentNo" : commentNo,
    					"commentContent" : commentContent },
    			success : function(result){
    				if(result>0){
    					swal({"icon" : "success" , "title" : "댓글 수정 성공"});
    					selectCommentList();
    				}
    			},
    			error : function(){
    				console.log("댓글 수정 실패");
    			}
    		})
    	}
    	
    	function deleteComment(commentNo){
    		if(confirm("정말로 삭제하시겠습니까?")){
    			var url = "${contextPath}/comment/deleteComment";
    		}
    		
    		$.ajax({
    			url : url,
    			data : {"commentNo" : commentNo},
    			success : function(result){
    				if(result>0){
    					selectCommentList(commentNo);
    					swal({"icon" : "success" , "title" : "댓글 삭제 성공"})
    				}
    			},
    			error :  function(){
    				console.log("댓글 삭제 실패");
    			}
    		})
    	}
    	

	//
    </script>
    
</body>
</html>