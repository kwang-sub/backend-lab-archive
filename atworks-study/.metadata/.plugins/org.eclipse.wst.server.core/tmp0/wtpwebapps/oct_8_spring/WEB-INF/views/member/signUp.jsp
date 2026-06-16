<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="contaner">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4"><br><br>
				<form method="post" action="/signUp" id ="signUpForm">
				  <div class="mb-3">
				    <label for="exampleInputEmail1" class="form-label">id</label>
				    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="mi_id"  >
				    <div id="id_check"></div>
				    
				  </div>
				  
				  <div class="mb-3">
				    <label for="exampleInputPassword1" class="form-label">Password</label>
				    <input type="password" class="form-control" id="exampleInputPassword1" name="mi_pw">
				    <div id="pw1_check"></div>
				  </div>
				  
				  <div class="mb-3">
				    <label for="exampleInputPassword2" class="form-label">PasswordCheck</label>
				    <input type="password" class="form-control" id="exampleInputPassword2">
				    <div id="pw2_check"></div>
				  </div>
				  <div class="mb-3">
				    <label for="mobile" class="form-label">mobile</label>
				    <input type="text" class="form-control" name="mi_mobile" id="mobile">
				  </div>
				  <div class="mb-3">
				    <label for="email" class="form-label">email</label>
				    <input type="email" class="form-control" id="email" name="mi_email">
				  </div>
				  
				  <div class="mb-3">
				    <label for="m" class="form-label">m</label>
				    <input type="radio" class=" gender" id="m" name="gender" value="M">
				    
				    <label for="w" class="form-label">w</label>
				    <input type="radio" class=" gender" id="w" name="gender" value="W">
				  </div>
				  
				  
				  
				  <button type="button" class="btn btn-primary" id="check-btn">회원가입</button>
				  <a href="#" class="btn btn-primary">취소하기</a>
				</form>
			
			</div>
			<div class="col-4"></div>
		</div>
	</div>
	
	<script type="text/javascript">
		
		
		var initialize = function(){
			var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
			var id = $("#exampleInputEmail1").val();
			var pw1 = $("#exampleInputPassword1").val();
			var pw2 = $("#exampleInputPassword2").val();
			var check = {
				"pw2" : false,
				"pw1" : false,
				"id" : false
			};
			
			//유효성검사 
			
			
			
			$("#exampleInputEmail1").on("input", function () {
				var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
				var id = $("#exampleInputEmail1").val();
				if(idReg.test(id)){
					
					$.ajax({
						type : "post",
						data : {
							mi_id : id
						},
						url : "/checkId",
						success : function(res){
							if(res.result == 0){
								$("#id_check").text("사용 가능한 아이디입니다").css("color","green");
								check.id = true;
								console.log(check)
							}else{
								$("#id_check").text("사용중인 아이디입니다").css("color","red");
								check.id = false;
							}
						}
					
					})
					
				}else{
					$("#id_check").text("유효하지 않는 아이디입니다").css("color","red");
					check.id = false;
					
				}
			})
			
			
			$("#exampleInputPassword1").on("input",function(){
				var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
				var pw1 = $("#exampleInputPassword1").val();
				if(idReg.test(pw1)){
					$("#pw1_check").text("사용 가능한 비밀번호입니다").css("color","green");
					check.pw1 = true;
				}else{
					$("#pw1_check").text("사용 불가능한 비밀번호입니다").css("color","red");
					check.pw1 = false;
				}
				
			})
			
			$("#exampleInputPassword2").on("input",function(){
				
				var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
				var pw1 = $("#exampleInputPassword1").val();
				var pw2 = $("#exampleInputPassword2").val();
				
				if(idReg.test(pw2) && pw1 === pw2 ){
					$("#pw2_check").text("사용 가능한 비밀번호입니다").css("color","green");
					check.pw2 = true;
				}else{
					$("#pw2_check").text("사용 불가능한 비밀번호입니다").css("color","red");
					check.pw2 = false;
				}
				
			})
			
			$("#check-btn").on("click", function () {
				
				var bool = true;
				for(var key in check){
					if(!check[key]){
						var msg = "";
						switch(key){
							
							case "id" :
								msg = "유효하지 않은 아이디입니다";
								bool = false;
								break;
							case "pw2" :
								msg ="유효하지 않은 비밀번호2입니다";
								bool = false;
								break;
							case "pw1" :
								msg ="유효하지 않은 비밀번호1입니다";
								bool = false;
								break;
						}
						
					}
				}
				if(bool){
					$("#signUpForm").submit();
				}else{
					alert(msg);
				}
				
				
			})
			
			 
		}
		
	
		
	</script>
