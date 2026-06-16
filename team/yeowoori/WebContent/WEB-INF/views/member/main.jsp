<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<c:set var="contextPath" scope="application"
	value="${pageContext.servletContext.contextPath }"/>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>main</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
</head>
	<style>
	 	* {  font-family: 'Spoqa Han Sans Neo', 'sans-serif'; }
        body{padding-top: 10px;}
        a{text-decoration: none; color:black;}
        a:hover{text-decoration: none; color:#A66129;}
        a:visited{
            text-decoration: none;
            color: black;
        }
        .jumbotron{
            
            background-image: url(resources/img/지도-기본.png);
            background-size : auto 100% ;
           	background-position : center center;
            background-repeat: no-repeat;
            background-color: white;
            
            height: 600px;
            position: relative;
        }
        .area{
            border: 1px rgb(197, 197, 197) solid;
        }
        .northwest{
            width: 150px;
            height: 180px;
            background-color: white;
            position: absolute;
            top: 80px;
            left: 250px;
            
        }
        .northeast{
            width: 150px;
            height: 130px;
            background-color: white;
            position: absolute;
            top: 0px;
            right: 320px;
            
        }
        .southwest{
            width: 150px;
            height: 130px;
            background-color: white;
            position: absolute;
            bottom: 80px;
            left: 320px;
            
        }
        .southeast{
            width: 180px;
            height: 200px;
            background-color: white;
            position: absolute;
            bottom: 30px;
            right: 200px;
            
        }
        .content{
            margin-left: 10px;
        }
        @media(max-width: 1300px) { 
            .northwest{
                top: 80px;
                left: 140px;
            }
            .northeast{
                top: 20px;
                right: 160px;
            }
            .southwest{
                bottom: 80px;
                left: 140px;
            }
            .southeast{
            bottom: 30px;
            right: 150px;
            
            }
        }
        @media(max-width: 730px) { 
           
            .northwest{
                top:30px;
                left: 50px;
            }
            .northeast{
                top: 0px;
                right: 20px;
            }
            .southwest{
                bottom: 80px;
                left: 50px;
            }
            .southeast{
            bottom: 30px;
            right: 40px;
            
            }
        }
    </style>
<body>
	<a href="${contextPath}/homeBoard">
		<img src="${contextPath}/resources/img/logo.png" style="width:150px; margin-top:60px; margin-left : 120px;">
	</a>
	<div style="display:inline-block; position:absolute; top:90px; line-height: 2; margin-left : 30px; color:grey;">
	    	지역을 선택해주세요.
	</div>
    
 <div class="row">
    <div class="col-1">
    </div>
    <div class="col-10 jumbotron jumbotron-fluid con" id="jumbotron">
        <a href="${contextPath}/homeBoard?area=1" style="color:black;">
            <div class="area northwest" id="northwest">
                <br>
                <div class="content">
                    <h3>서북</h3>
                    <p style="font-size: 12px;">
                        연남동, 망원/서교, 홍대,<br>
                        연희, 합정, 은평구, <br>공덕역,상수, 상암, 이대, <br>
                        신수동, 신촌, 독립문<br><br><br>
                    </p>
                </div>
            </div>

        </a>
        <a href="${contextPath}/homeBoard?area=4" style="color:black;">
            <div class="area northeast" id="northeast">
                <br>
                <div class="content">
                    <h3>동북</h3>
                    <p style="font-size: 12px;">
                        성수, 성북구, 건대<br>
                        동대문, 옥수, 왕십리
                    </p>
                </div>
            </div>

        </a>
        <a href="${contextPath}/homeBoard?area=2" style="color:black;">
            <div class="area southwest" id="southwest">
                <br>
                <div class="content">
                    <h3>서남</h3>
                    <p style="font-size: 12px;">
                        문래동, 여의도, 영등포,<br>
                        구로, 마곡
                    </p>
                </div>
            </div>

        </a>
        <a href="${contextPath}/homeBoard?area=3" style="color:black;">
            <div class="area southeast" id="southeast">
                <br>
                <div class="content">
                    <h3>동남</h3>
                    <p style="font-size: 12px;">
                        압구정, 청담, 가로수길, 잠실,<br>
                        송리단길, 삼성역, 강동구, <br>
                        논현, 역삼동, 강남역,<br>
                        고속터미널, 양재 ,장원, <br>
                        서래마을, 대치동
                    </p>
                </div>
            </div>

        </a>
        
    </div>
    <div class="col-1">
      
    </div>
  </div>
</div>
    
    
    <script>
        $(document).ready(function(){

            const northwest = document.getElementById("northwest");
            const northeast = document.getElementById("northeast");
            const southwest = document.getElementById("southwest");
            const southeast = document.getElementById("southeast");
            const jumbotron =  document.getElementById("jumbotron");
            northwest.addEventListener("mouseover",function(){
                $("#jumbotron").css("background-image" , "url(resources/img/지도-서북.png)");
            });
            northwest.addEventListener("mouseout",function(){
                $("#jumbotron").css("background-image" , "url(resources/img/지도-기본.png)");
            });


            northeast.addEventListener("mouseover",function(){
                $("#jumbotron").css("background-image" , "url(resources/img/지도-동북.png)");
            });
            northeast.addEventListener("mouseout",function(){
                $("#jumbotron").css("background-image" , "url(resources/img/지도-기본.png)");
            });


            southwest.addEventListener("mouseover",function(){
                $("#jumbotron").css("background-image" , "url(resources/img/지도-서남.png)");
            });
            southwest.addEventListener("mouseout",function(){
                $("#jumbotron").css("background-image" , "url(resources/img/지도-기본.png)");
            });

            southeast.addEventListener("mouseover",function(){
                $("#jumbotron").css("background-image" , "url(resources/img/지도-동남.png)");
            });
            southeast.addEventListener("mouseout",function(){
                $("#jumbotron").css("background-image" , "url(resources/img/지도-기본.png)");
            });
        })
    </script>






	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>