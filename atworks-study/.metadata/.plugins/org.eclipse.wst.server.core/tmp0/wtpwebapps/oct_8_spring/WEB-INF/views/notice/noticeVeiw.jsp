<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="contaner">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<form action="/write" method="post">
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">title</label>
				  <input name="ni_title" type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlTextarea1" class="form-label">Content</label>
				  <textarea name="ni_content" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
				</div>
				
				<button type="submit" class="btn btn-primary">글작성</button>
			</form>
		</div>
		<div class="col-3"></div>
	</div>
</div>
