<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="contaner">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4"><br><br>
				<form method="post" action="/login">
				  <div class="mb-3">
				    <label for="exampleInputEmail1" class="form-label">id</label>
				    <input type="input" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="mi_id">
				    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
				  </div>
				  <div class="mb-3">
				    <label for="exampleInputPassword1" class="form-label">Password</label>
				    <input type="password" class="form-control" id="exampleInputPassword1" name="mi_pw">
				  </div>
				  <div class="mb-3 form-check">
				    <input type="checkbox" class="form-check-input" id="exampleCheck1">
				    <label class="form-check-label" for="exampleCheck1">Check me out</label>
				  </div>
				  <button type="submit" class="btn btn-primary">login</button>
				  <a href="/signUp" class="btn btn-primary" id="goJoin">sign Up</a>
				</form>
			
			</div>
			<div class="col-4"></div>
		</div>
	</div>
	
	
	

	
	
	<script>
		var initialize = function(){
			
		}
	
	
	</script>
