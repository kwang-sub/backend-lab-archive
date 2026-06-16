<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	
	<style>
        * { font-family: 'Spoqa Han Sans Neo', 'sans-serif'; }
        a{
            color : black;
        }
        .row2{
            height: 800px;
            
        }
        .content-img{
            height: 50px;
            width: 50px;
            text-align: center;
            line-height: 80px;

        }
        .div-border img{
            
            
            width: 50px;
            height: 50px;
        }
        .content-text{
            height: 80px;
            line-height: 80px;
        }
        .div-border div{
            border: solid 1px gray;
        }
        .category-selected{
            background-color: #A66129;
            color:white;
            font-weight: 500;
        }
       .category-unselected{
            color: #A66129;
            font-weight: 500;
       }
       .category-btn>button{
           margin-left: 25px;
       }

    </style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

	<div class="container">

        <form class="category-btn" action="#" method="GET">
            <div style="padding:10px;"></div>
            <h6 style="display: inline-block; font-weight: bold;">마이페이지</h6>
            <button class="btn btn-light category-unselected" name="category" value="전체">나의활동</button>
            <button class="btn btn-light category-unselected" name="category" value="분위기맛집">좋아요</button>
            <button class="btn btn-light category-selected" name="category" value="산책코스">알림</button>
            <button class="btn btn-light category-unselected" name="category" value="커플데이트">설정</button>
            <hr  align="center" style="border: solid #b6957c; border-width: 1px 0px 0px 0px; width:100%">
            <div style="padding : 20px"></div>
        </form>
        
    
        <div class="row row2">
            <div class="col-2">
            
            </div>
            <div class="col-8 div-border" >
                <div class="row" style="height: 100px; "><h3 style="line-height: 90px; margin-left: 50px; font-weight: 500;">내 소식</h3></div>
                <div class="row ">
                    <span class="content-img col-2">
                        <img src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fdata.whicdn.com%2Fimages%2F310738005%2Foriginal.jpg&type=a340" alt="" class=" rounded-circle">
                    </span>
                    <span class="content-text col-10">
                        유저닉네임님 외 5명이 회원님의 게시글을 좋아합니다<br>
                    </span>
                </div>
                <div class="row ">
                    <span class="content-img col-2">
                        <img src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fdata.whicdn.com%2Fimages%2F310738005%2Foriginal.jpg&type=a340" alt="" class=" rounded-circle">
                    </span>
                    <span class="content-text col-10">
                        유저닉네임님 외 5명이 회원님의 게시글을 좋아합니다<br>
                    </span>
                </div>
                
                
            </div>
            <div class="col-2">
              
            </div>
          </div>
    </div>
</body>
</html>