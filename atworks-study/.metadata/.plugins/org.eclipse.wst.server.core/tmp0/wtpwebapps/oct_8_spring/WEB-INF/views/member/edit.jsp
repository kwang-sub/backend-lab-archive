<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="contaner">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-6"><br><br>
				<form method="post" action="/edit" >
				  <div class="mb-3">
				    <label for="exampleInputEmail1" class="form-label">id</label>
				    <input type="input" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="mi_id" value="${loginMember.mi_id }">
				  </div>
				  
				  <div class="mb-3">
				    <label for="exampleInputPassword1" class="form-label">Password</label>
				    <input type="password" class="form-control" id="exampleInputPassword1" name="mi_pw" value="${loginMember.mi_pw}">
				  </div>
				  
				  
				  <div class="mb-3">
				    <label for="mobile" class="form-label">mobile</label>
				    <input type="text" class="form-control" name="mi_mobile" id="mobile" value="${loginMember.mi_mobile }">
				  </div>
				  <div class="mb-3">
				    <label for="email" class="form-label">email</label>
				    <input type="email" class="form-control" id="email" name="mi_email" value="${loginMember.mi_email }">
				  </div>
				  
				  <div class="mb-3">
				    <label for="m" class="form-label">${loginMember.mi_gender}</label>
				  </div>
				  
				  
				  
				  <button type="submit" class="btn btn-primary" id="check-btn">수정하기</button>
				  
				  <input type="hidden" name="mi_no" value="${loginMember.mi_no}" id="mi_no">
				</form>
				
				
					<button type="button" class="btn btn-danger" id="del-button" >탈퇴하기</a>
					
			
			</div>
			<div class="col-4"></div>
		</div>
	</div>
	
	<script type="text/javascript">
		
		
		var initialize = function(){
			
			//유효성검사 
			
			$("#del-button").click(function () {
				
				var no = $("#mi_no").val();
				console.log(no);
				
				$.ajax({
				    type : 'POST',
				    url : "/signOut",
				    data : {
				    	mi_no : no
				    },
				    
				    success : function(data) {
				    	console.log(data)
				    	 if(data == 1){
					        alert("success!" + data);
					        location.href = "/";
				    	}else{
				    		alert("실패하였습니다.")
				    	} 
				    }
			
				});


			}); 
			 
		}
	</script>
