<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading"> Board Read Page</div>
			<div class="panel-body">
				<div class="form-group">
					<label>Bno</label> <input class="form-control" name="bno" value="${board.bno }" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>Title</label> <input class="form-control" name="title" value="${board.title }" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>Text area</label> <textarea rows="3" class="form-control" name="content" readonly="readonly">${board.content }</textarea>
				</div>
				<div class="form-group">
					<label>Writer</label> <input class="form-control" name="writer" readonly="readonly" value="${board.writer }">
				</div>
				<button data-oper='modify' class="btn btn-default" >Modify</button>
				<button data-oper='list' class="btn btn-info" >List</button>
				<form action="/board/modify" method="get" id="operForm">
					<input type="hidden" value="${board.bno }" id="bno" name="bno">
					<input type="hidden" value="${cri.pageNum}" id="bno" name="pageNum">
					<input type="hidden" value="${cri.amount }" id="bno" name="amount">
					<input type="hidden" value="${cri.type }" name="type">
					<input type="hidden" value="${cri.keyword }" name="keyword">
					
					
				</form>
			</div>
			
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () {
		
		var operForm = $("#operForm");
		$("button[data-oper='modify']").on("click",function(e){
			operForm.attr("action","/board/modify").submit();
		})
		$("button[data-oper='list']").on("click",function(e){
			/* 파라미터로 bno안넘어가게 하기 위함 */
			operForm.find("#bno").remove();
			operForm.attr("action","/board/list").submit();
		})
	})
</script>
<%@include file="../includes/footer.jsp" %>
