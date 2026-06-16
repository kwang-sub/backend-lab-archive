// 회원 가입 유효성 검사

// 각 유효성 검사 결과를 저장할 객체
const checkObj = {

	"newPwd1": false,
	"newpwd2": false,

};


// -------------------------------------- 비밀번호 변경시
// 비밀번호 유효성 검사
// 조건 : 영어, 숫자, 특수문자(# , -, _) 6~20글자
$("#newPwd1").on("input", function() {
	const regExp = /^[a-zA-Z0-9\#\-\_]{8,20}$/;
	const inputPwd1 = $(this).val().trim();

	if (regExp.test(inputPwd1)) {
		$("#checkNewPwd1").text("유효한 비밀번호 형식입니다.").css("color", "green");

		checkObj.newPwd1 = true;
	} else {
		$("#checkNewPwd1").text("비밀번호가 유효하지 않습니다.").css("color", "red");

		checkObj.newPwd1 = false;
	}
});

// 비밀번호, 비밀번호 확인 일치 여부 판단
$("#newPwd2,  #newPwd1").on("input", function() {

	const pwd1 = $("#newPwd1").val();
	const pwd2 = $("#newPwd2").val();

	if (pwd1.trim() == "" && pwd2.trim() == "") { // 둘다 비었을 때
		$("#checkNewPwd1").html("&nbsp;"); // &nbsp;  == 띄어쓰기
		$("#checkNewPwd2").html("&nbsp;");

		checkObj.newPwd2 = false;

	} else if (pwd1 == pwd2) {
		$("#checkNewPwd2").text("비밀번호 일치").css("color", "green");

		checkObj.newPwd2 = true;

	} else {
		$("#checkNewPwd2").text("비밀번호 불일치").css("color", "red");

		checkObj.newPwd2 = false;
	}
});


// 회원 가입 버튼 클릭 시 전체 유효성 검사 여부 확인
function validate() {


	for (const key in checkObj) {

		if (!checkObj[key]) { // false 값을 가진 속성이 있을 경우
			let msg;
			switch (key) {

				case "newPwd1":
					msg = "비밀번호가 유효하지 않습니다.";
					break;
				case "newPwd2":
					msg = "비밀번호가 일치하지 않습니다. ";
					break;

			}

			// msg에 담긴 내용 출력
			swal(msg).then(function() {

				const selector = "#" + key;

				$(selector).focus();
				// 유효하지 않은 값을 입력한 부분으로 포커스 이동
			});

			return false;  // submit 이벤트 제거(회원가입 실행 X)

		}
	} // for end
}





