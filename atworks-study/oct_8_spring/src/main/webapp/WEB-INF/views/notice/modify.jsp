<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
   <div class="contaner">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<form action="/notice/modify" method="post">
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">ni_no</label>
				  <input disabled name="ni_no" type="text" class="form-control" id="exampleFormControlInput1"  value="${notice.ni_no}">
				</div>
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">title</label>
				  <input name="ni_title" type="text" class="form-control" id="exampleFormControlInput1"  value="${notice.ni_title}">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlTextarea1" class="form-label">Content</label>
				  <textarea  name="ni_content" class="form-control" id="exampleFormControlTextarea1" rows="3" >${notice.ni_content}</textarea>
				</div>
				
				<input type="hidden" name="ni_no" value="${notice.ni_no}">
				
				<button class="btn btn-primary"type="submit">¿Ï·á</button>
			</form>
			
			
			
		</div>
		<div class="col-3"></div>
	</div>
</div>