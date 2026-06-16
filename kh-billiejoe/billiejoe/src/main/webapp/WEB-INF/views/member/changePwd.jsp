<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<style>
.changepw a {
	text-decoration: none;
	color: black;
}

.changepw h6 {
	width: 110%;
}

.changepw #update-btn {
	margin-left: 20%;
	width: 25%;
}
</style>

	<!-- // header.jsp -->
	<!-- 본문 시작 -->
	<div class="container py-5">
		<div class="content-header row">
			<h2 class="col">비밀번호 변경</h2>
		</div>
		<div class="row">
			<jsp:include page="../common/mypageSidebar.jsp"></jsp:include>

			<div class="col-md-9 changepw">

				<div class="bg-white rounded  container p-3">
					<form method="POST" action="${contextPath}/member/changePwd"
						onsubmit="return validate();" class="form-horizontal" role="form">



						<div class="row mb-3 form-row">
							<div class="col-md-2">
								<h6>현재 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="currentPwd"
									name="currentPwd" placeholder="현재 비밀번호를 입력하세요">
							</div>
						</div>
						<div class="col-md-6 offset-md-3">
							<span>&nbsp;</span>
						</div>

						<div class="row mb-3 form-row">
							<div class="col-md-2">
								<h6>새로운 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="newPwd1"
									name="newPwd1" placeholder="영문/숫자/특수문자 혼합 8자리 이상">
							</div>
							<div class="col-md-6 offset-md-3">
								<span id="checkNewPwd1">&nbsp;</span>
							</div>
						</div>
						<div class="row mb-3 form-row">
							<div class="col-md-2">
								<h6>새 비밀번호(확인)</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="newPwd2"
									name="newPwd2" placeholder="새 비밀번호를 다시 입력하세요">
							</div>

							<div class="col-md-6 offset-md-3">
								<span id="checkNewPwd2">&nbsp;</span>
							</div>
						</div>

						<div class="col-md-6 offset-md-3">
							<span>&nbsp;</span>
						</div>

						<button class="btn btn-md btn-block btn-primary" type="submit"
							id="update-btn">변경하기</button>

					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script src="${contextPath}/resources/js/changePwd.js"></script>