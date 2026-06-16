<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath }"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
    
    <title>header</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <style type="text/css">
        * {  font-family: 'Spoqa Han Sans Neo', 'sans-serif'; }
        body{padding-top: 50px;}
        a{text-decoration: none; color:black;}
        a:hover{text-decoration: none; color:#A66129;}
        a:visited{
            text-decoration: none;
            color: black;
        }
        #icons{
        	position: fixed; top: 0; left: 0; right: 0; bottom: 0; 
	        padding-top: 10px; padding-left: 6%; padding-right: 6%; padding-bottom:10px; 
	        background-color: white;
	        height: 50px;
	        z-index: 1;
	     } 
        .icon{margin-left: 30px; float: right; }
        .menu{font-weight: 500; padding-right: 30px;}
        .color-grey{ color:grey; }
        #search-form{
            display: none;
            float:left; 
            margin-left: 10px;
        }
        #search-bar{
            border: 1px solid grey; border-radius: 5px; 
            width: 150px; height: 25px; 
            box-sizing: border-box;
        }
        #search-bar-btn{
            padding-left: 15px; 
            padding-right: 0px;
            padding-top: 0px;
            margin-top: 0px;
            margin-right: 0px;
        }

        
</style>
</head>
    <body>
        <div id="icons">
            <c:choose>
            	<c:when test="${empty loginMember}">
            		<a href="${contextPath}/login" class="icon" id="user-icon"><img src="${contextPath}/resources/img/user.png" height="20px"></a>
            	</c:when>
            	<c:otherwise>
            		<div class="dropdownleft" style="float:right; margin:0px; padding:0px;">
					  <button class="btn" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin:0px; padding:0px;">
					    <span class="icon" id="user-icon"><img src="${contextPath}/resources/img/user.png" height="20px"></span>
					  </button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					    <a class="dropdown-item" href="${contextPath}/member/myPage/my">마이페이지</a>
					    <a class="dropdown-item" href="${contextPath}/member/myPage/logout">로그아웃</a>
					  </div>
					</div>
            	</c:otherwise>
            </c:choose>
            <c:if test="${!empty loginMember }">
            <a href="${contextPath}/member/myPage/like" class="icon" id="heart-icon"><img src="${contextPath}/resources/img/like.png" height="20px"></a>
            </c:if>
            
            
            <div class="icon" id="search-icon">
                <img id="search-btn" src="${contextPath}/resources/img/search.png" height="20px">
                <form id="search-form" action="${contextPath }/search/all" method="GET" onsubmit="return checkSearch();">
                    <input  type="search" id="search-bar" name="search" placeholder="search">
                    <button id="search-bar-btn" style="background-color: white; border: white;"><img src="${contextPath}/resources/img/search.png" height="20px" ></button>
                </form>
            </div>
            
  			<c:if test="${!empty param.area }">
  				<c:if test="${param.area ==1 }">
  			   	<div class="icon" id="area-icon" style="padding:0px;" >
	                <div style="background-color: black; width: 40px; text-align: center; border-radius: 5px; color: white; font-weight: 500; display: inline-block; font-size: 13px;">
	                    <span style="position: relative; top: 1px;">서북</span>
	                </div>
	            </div>
  				</c:if>
  				<c:if test="${param.area ==2 }">
  			   	<div class="icon" id="area-icon" style="padding:0px;" >
	                <div style="background-color: black; width: 40px; text-align: center; border-radius: 5px; color: white; font-weight: 500; display: inline-block; font-size: 13px;">
	                    <span style="position: relative; top: 1px;">서남</span>
	                </div>
	            </div>
  				</c:if>
  				<c:if test="${param.area ==3 }">
  			   	<div class="icon" id="area-icon" style="padding:0px;" >
	                <div style="background-color: black; width: 40px; text-align: center; border-radius: 5px; color: white; font-weight: 500; display: inline-block; font-size: 13px;">
	                    <span style="position: relative; top: 1px;">동남</span>
	                </div>
	            </div>
  				</c:if>
  				<c:if test="${param.area ==4 }">
  			   	<div class="icon" id="area-icon" style="padding:0px;" >
	                <div style="background-color: black; width: 40px; text-align: center; border-radius: 5px; color: white; font-weight: 500; display: inline-block; font-size: 13px;">
	                    <span style="position: relative; top: 1px;">동북</span>
	                </div>
	            </div>
  				</c:if>
  			
	            
  			</c:if>
        </div>
        
        <div class="header navbar navbar-expand-lg navbar-light bg-white ">
            <div class="container">
                <a class="navbar-brand" href="${contextPath}/homeBoard"><img src="${contextPath}/resources/img/logo.png" style="width: 150px;"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <ul class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav mr-auto">
                    	<c:if test="${!empty param.area}">
                    	<li class="nav-item active menu"><a class="nav-link" href="/yeowoori/board/list4?type=4">공지</a></li>
                        <li class="nav-item active menu"><a class="nav-link" href="/yeowoori/board/list?type=1&area=${param.area}">여행</a></li>
                        <li class="nav-item active menu"><a class="nav-link" href="/yeowoori/board/list2?type=2&area=${param.area}">질문</a></li>
                        <li class="nav-item active menu"><a class="nav-link" href="/yeowoori/board/list3?type=3&area=${param.area}">같이 떠나요</a></li>
                    	</c:if>
                    	<c:if test="${empty param.area}">
                    	<li class="nav-item active menu"><a class="nav-link" href="/yeowoori/board/list4?type=4">공지</a></li>
                        <li class="nav-item active menu"><a class="nav-link" href="/yeowoori/board/list?type=1">여행</a></li>
                        <li class="nav-item active menu"><a class="nav-link" href="/yeowoori/board/list2?type=2">질문</a></li>
                        <li class="nav-item active menu"><a class="nav-link" href="/yeowoori/board/list3?type=3">같이 떠나요</a></li>
                    	</c:if>
                        
                    </ul>
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link " href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              다른 지역 보기
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                              <a class="dropdown-item color-grey" href="${contextPath}/homeBoard">전체</a>
                              
                              <a class="dropdown-item color-grey" href="${contextPath}/homeBoard?area=1">서북</a>
                              <a class="dropdown-item color-grey" href="${contextPath}/homeBoard?area=2">서남</a>
                              <a class="dropdown-item color-grey" href="${contextPath}/homeBoard?area=3">동남</a>
                              <a class="dropdown-item color-grey" href="${contextPath}/homeBoard?area=4">동북</a>
                              
       
                            </div>
                          </li>
                    </ul>
                </div>
            </div>
        </div>
        <hr  align="center" style="border: solid #b6957c; border-width: 1px 0px 0px 0px; width:100%">
        <script>
            
            $("#search-btn").on("click",function(){
                $("#search-form").css("display","inline-block");
                $("#search-btn").css("display","none");
                $("#search-bar").focus(); 
            })
            $(document).ready(function(){
                $(document).mousedown(function( e ){
                    if( $("#search-form").is(":visible") ) {
                        $("#search-form").each(function(){
                            var l_position = $(this).offset();
                            l_position.right = parseInt(l_position.left) + ($(this).width());
                            l_position.bottom = parseInt(l_position.top) + parseInt($(this).height());

                            if( ( l_position.left <= e.pageX && e.pageX <= l_position.right )
                                && ( l_position.top <= e.pageY && e.pageY <= l_position.bottom ) ) {
                            } else {
                                $(this).hide();
                                $("#search-btn").css("display","inline-block");
                            }
                        });
                    }
                });
            });
        </script>

        <%-- 로그인 실패와 같은 메시지가 서버로부터 전달되어 온 경우 출력 --%>
	    <c:if test="${!empty title }">
			<script>
				swal({
					"icon" : "${icon}",
					"title" : "${title}",
					"text" : "${text}"
				})
			</script>
			<c:remove var="text"/>
			<c:remove var="icon"/>
			<c:remove var="title"/>
		</c:if>
        <script>
        function checkSearch(){
        	
        	if($("#search-bar").val().trim()==""){
        		swal({
        			"icon" : "warning",
        			"title" : "검색할 내용이 없습니다."
        		})
        		$(this).val("");
        		return false;
        	}
        	
        	
        }
        
        
        </script>

</body>
</html>