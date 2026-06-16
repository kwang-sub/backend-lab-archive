<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <style>
        .content>form>div {
            height: 50px;
        }

        .area input[type=radio] {
            display: none;
        }

        .area input[type=radio]+label {
            height: 30px;
            padding-left: 10px;
            padding-right: 10px;
            padding-top: 4px;
            background-color: #EDEDED;
            color: #A66129;
            font-weight: 500;
            font-size: 14px;
            border-radius: 10px;
            margin-right: 5px;
        }

        .area input[type=radio]:checked+label {
            height: 30px;
            width: fit-content;
            padding-left: 10px;
            padding-right: 10px;
            padding-top: 4px;
            background-color: #A66129;
            color: white;
            font-weight: 500;
            font-size: 14px;
            border-radius: 10px;
            margin-right: 5px;
            margin-top: 0px;
        }

        .file {
            display: none;
        }

        .photo {
            height: 30px;
            padding-left: 10px;
            padding-right: 10px;
            padding-top: 4px;
            background-color: #EDEDED;
            color: #A66129;
            font-weight: 500;
            font-size: 14px;
            border-radius: 10px;
            margin-right: 5px;
        }

        #btn-label {
            background-color: #A66129;
            color: white;
            font-size: 16px;
            text-align: center;
            width: 200px;
            max-width: 20%;
            height: 40px;
            border-radius: 5px;
            margin-top: 0px;
        }



        body {
            margin: 10px
        }

        .where {
            display: block;
            margin: 25px 15px;
            font-size: 11px;
            color: #000;
            text-decoration: none;
            font-family: verdana;
            font-style: italic;
        }

        .filebox input[type="file"] {
            position: absolute;
            width: 1px;
            height: 1px;
            padding: 0;
            margin: -1px;
            overflow: hidden;
            clip: rect(0, 0, 0, 0);
            border: 0;
        }

        .filebox label {
            display: inline-block;
            padding: .5em .75em;
            color: #999;
            font-size: inherit;
            line-height: normal;
            vertical-align: middle;
            background-color: #fdfdfd;
            cursor: pointer;
            border: 1px solid #ebebeb;
            border-bottom-color: #e2e2e2;
            border-radius: .25em;
        }

        /* named upload */
        .filebox .upload-name {
            display: inline-block;
            padding: .5em .75em;
            font-size: inherit;
            font-family: inherit;
            line-height: normal;
            vertical-align: middle;
            background-color: #f5f5f5;
            border: 1px solid #ebebeb;
            border-bottom-color: #e2e2e2;
            border-radius: .25em;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
        }

        .filebox.bs3-primary label {
            color: #fff;
            background-color: #A66129;
            border-color: #A66129;
        }

        .photobox {
            margin-left: 70px;
        }

        .boardImg{
  	cursor : pointer;
		width: 200px;
		height: 200px;
		border : 1px solid #ced4da;
		position : relative;
	}
	
	.thubnail{
		width: 300px;
		height: 300px;
	}
	
	.boardImg > img{
		max-width : 100%;
		max-height : 100%;
		position: absolute;
		top: 0;
		bottom : 0;
		left : 0;
		right : 0;
		margin : auto;
	}
	
	
	#fileArea{
		display : none;
	}
	
    </style>
</head>

<body>
    <div style="padding : 20px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-xs-1">
            </div>
            <div class="col-12 col-lg-12">
            
            	<c:choose>
            	
            		<c:when test="${param.type == '1' }">
            			<c:if test="${empty param.area}">
            			<form action="${contextPath}/board2/update?type=${param.type}" role="form" onsubmit="return boardcheck2();" enctype="multipart/form-data" method="post" >
            			</c:if>
            			<c:if test="${!empty param.area}">
            			<form action="${contextPath}/board2/update?type=${param.type}&area=${param.area}" role="form" onsubmit="return boardcheck2();" enctype="multipart/form-data" method="post" >
            			</c:if>
            		</c:when>
            		
            		<c:otherwise>
            			<c:if test="${empty param.area }">
            			<form action="${contextPath}/board2/update?type=${param.type}" role="form" onsubmit="return boardcheck();" enctype="multipart/form-data" method="post" >
            			</c:if>
                		
            			<c:if test="${!empty param.area }">
            			<form action="${contextPath}/board2/update?type=${param.type}&area=${param.area}" role="form" onsubmit="return boardcheck();" enctype="multipart/form-data" method="post" >
            			</c:if>
            		</c:otherwise>
            		
            	</c:choose>
							
                    <div >
	                    <c:choose>
	                    	<c:when test="${param.type==1 }">
	                        <h3>여행 게시판</h3>
	                    	</c:when>
	                    	<c:when test="${param.type==2 }">
	                        <h3>질문 게시판</h3>
	                    	</c:when>
	                    	<c:when test="${param.type==3 }">
	                        <h3>같이 떠나요 게시판</h3>
	                    	</c:when>
	                    </c:choose>
                    </div>
                    
                    <div style="padding : 10px;"></div>
                    <div>
                        <input type="text" name="title" id="title" size="80" value="${board.boardTitle }"
                            style="height: 40px; width: 500px; max-width: 55%;">
                        <button type="submit" class="btn" id="btn-label" style="float:right;">수정하기</button>
                    </div>
                    
                    <div style="padding :5px;"></div>
                    
                    	<div>
	                        <div style="color: grey; display: inline-block; margin-right: 10px; margin-bottom: 10px; ">태그 선택
	                        </div>
	                        <div class="area" style="display: inline-block; margin-bottom: 10px;">
	                            <input type="radio" id="mood" name="category" value="1">
	                            <label for="mood">분위기맛집</label>
	                            <input type="radio" id="walk" name="category" value="2">
	                            <label for="walk">산책코스</label>
	                            <input type="radio" id="couple" name="category" value="3">
	                            <label for="couple">커플데이트</label>
	                            <input type="radio" id="cafe" name="category" value="4">
	                            <label for="cafe">카페투어</label>
	                            <input type="radio" id="etc" name="category" value="5">
	                            <label for="etc">기타</label>
	                            <!-- checked 확인하기 -->
	                        </div>
	                    </div>
                    <div>
                        <div style="color: grey; display: inline-block; margin-right: 10px; margin-bottom: 10px;">지역 선택
                        </div>
                        <span class="area">
                            <input type="radio" id="northwest" name="area" value="1">
                            <label for="northwest">서북</label>
                            <input type="radio" id="southwest" name="area" value="2" >
                            <label for="southwest">서남</label>
                            <input type="radio" id="southeast" name="area" value="3">
                            <label for="southeast">동남</label>
                            <input type="radio" id="northeast" name="area" value="4">
                            <label for="northeast"> 동북 </label>
                            <!-- checked 확인하기 -->
                        </span>
                    </div>
                    <div style="padding :5px;"></div>
                    <div>
                        <span style="display:inline-block; margin-right: 22px;">
                            <img src="https://drive.google.com/uc?id=1HWSNypdXLIN0wOeCFaH7LdFvwCpMkejD" alt="photo"
                                style="height: 30px;">
                        </span>


						<c:forEach items="${board.atList }" var="at">
						<c:choose>
							<c:when test="${at.fileLevel == 0 && !empty at.fileNm}">
							<c:set var="img0" value="${contextPath}/${at.filePath}${at.fileNm}"/>
							</c:when>		
							<c:when test="${at.fileLevel == 1 && !empty at.fileNm}">
							<c:set var="img1" value="${contextPath}/${at.filePath}${at.fileNm}"/>
							</c:when>		
							<c:when test="${at.fileLevel == 2 && !empty at.fileNm}">
							<c:set var="img2" value="${contextPath}/${at.filePath}${at.fileNm}"/>
							</c:when>		
							<c:when test="${at.fileLevel == 3 && !empty at.fileNm}">
							<c:set var="img3" value="${contextPath}/${at.filePath}${at.fileNm}"/>
							</c:when>		
						</c:choose>
						</c:forEach>


                        <span>
                            <span class="filebox bs3-primary">
                                <label for="thumbnail">대표</label>
                                <input class="upload-name" value="${board.atList[0].fileNm}" disabled="disabled" style="width: 300px;">

                                <input type="file" id="thumbnail" name="img0" class="upload-hidden" >
                            </span>
                        </span>
                        
                    </div>
                    
						<div class="form-inline mb-2">
                            <label class="input-group-addon mr-3 insert-label">업로드<br>이미지</label>
                            <div class="mr-2 boardImg" id="contentImgArea">
                                <img id="contentImg1" src="${img1}">
                            </div>
        
                            <div class="mr-2 boardImg" id="contentImgArea1">
                                <img id="contentImg2" src="${img2}">
                            </div>
        
                            <div class="mr-2 boardImg" id="contentImgArea2">
                                <img id="contentImg3" src="${img3}">
                            </div>
                        </div>
        
        
                        <!-- 파일 업로드 하는 부분 -->
                        <div id="fileArea">
                            <!--  multiple 속성
                                - input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시 (파일 여러개 선택 가능)
                             -->
                            <input type="file" id="img0" name="img1" onchange="LoadImg(this,0)"> 
                            <input type="file" id="img1" name="img2" onchange="LoadImg(this,1)"> 
                            <input type="file" id="img2" name="img3" onchange="LoadImg(this,2)"> 
                           
                        </div>
                    </div>
                    
                        <textarea  style="width : 100%; height: 400px; resize: none;"id="summernote" name="content" class="summernote">${board.boardContent}</textarea>
                    	
                    	<input type="hidden" name="cp" value="${param.cp }">
                    	<input type="hidden" name="boardNo" value="${board.boardNo }">
                    	
                    	
                    	
                </form>
            </div>

            <div class="col-xs-1">

            </div>
        </div>
    </div>
    <div style="padding : 40px;"></div>
    <script>
        function boardcheck(){
            if($("#title").val().trim().length == 0){
                alert("제목을 입력해주세요");
                return false;
            }
            if($("#summernote").val().trim().length == 0){
                alert("내용을 입력해주세요");
                return false;
            }
        } 
        function boardcheck2(){
            if($("#title").val().trim().length == 0){
                alert("제목을 입력해주세요");
                return false;
            }
            if($("#summernote").val().trim().length == 0){
                alert("내용을 입력해주세요");
                return false;
            }
           
        } 

        $(document).ready(function () {
            var fileTarget = $('.filebox .upload-hidden');
            var photoTarget = $('.photobox .upload-hidden');
            
            
            fileTarget.on('change', function () {
                if (window.FileReader) {
                    var filename = $(this)[0].files[0].name;
                } else {
                    var filename = $(this).val().split('/').pop().split('\\').pop();
                }
                
                $(this).siblings('.upload-name').val(filename);
            });
            
            
        });
        
        
          
            

		$(function() {
			$(".boardImg").on("click", function() {
                var index = $(".boardImg").index(this);
				$("#img" + index).click();
			});    
            
		});    
        
		function LoadImg(value, num) {
            if (value.files && value.files[0]) {
                var reader = new FileReader();
                
				reader.readAsDataURL(value.files[0]);
				reader.onload = function(e) {
                    
                    $(".boardImg").eq(num).children("img").attr("src",
                            e.target.result);
				}            
                
			}    
		}    
		
		//카테고리 이전 게시글 입력된 값으로 세팅
		
		const category = "${board.categoryCode}"; 
		$("input[name='category']").each(function(index, item){
			if($(item).val() == category){
				$(item).prop("checked", true);
			}
		});
		
		const area = "${board.areaCode}";
		$("input[name='area']").each(function(index, item){
			if($(item).val()==area){
				$(item).prop("checked", true);
			}
		});

        
        </script>
</body>

</html>