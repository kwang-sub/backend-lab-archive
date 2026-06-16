// ------------------------------------------------------------------------------------------------------ 회원가입 js

// 각 유효성 검사 결과를 저장할 객체
const checkObj = {
	"email": false,
	"pwd1": false,
	"pwd2": false,
	"inputName": false
};

// 이메일 유효성 검사
// 조건 : 아이디 4글자 이상, 이메일 형식  ->   /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;

$("#email").on("input", function() {

	// [\w] : 아무글자
	const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;

	// 이벤트 핸들러 내부에 작성된 this == 이벤트가 발생한 요소 == $("#name")
	const inputEmail = $("#email").val().trim();

	if (regExp.test(inputEmail)) {
		// 이메일 중복 검사
		$.ajax({
			url: "emailDupCheck",         // 요청 주소(필수로 작성!)
			data: { "email": inputEmail },    // 전달하려는 값(파라미터)
			type: "post",              // 데이터 전달 방식

			success: function(result) {
				// 매개변수 result : 서버에서 비동기로 전달 받은 응답 데이터
				console.log(result);

				if (result > 0) { // 아이디가 중복된 경우
					$("#checkEmail").text("이미 사용중인 이메일(ID)입니다.").css("color", "red");
					checkObj.email = false;

				} else { // 아이디가 중복되지 않는 경우
					$("#checkEmail").text("사용 가능한 이메일(ID) 입니다.").css("color", "green")
					checkObj.email = true;
				}

			}, // 비동기 통신 성공 시 동작

			error: function(e) {
				// 매개변수 e : 예외 발생 시 Exception 객체를 전달 받을 변수
				console.log("ajax 통신 실패");
				console.log(e);

			} // 비동기 통신 실패 시 동작

		});

	} else {
		$("#checkEmail").text("이메일 형식으로 적어주세요.").css("color", "red");
		checkObj.email = false;
	}

});

// 비밀번호 유효성 검사
$("#pwd1").on("input", function() {

	// 조건 :  영어, 숫자, 특수문자 혼합 8자리 이상
	const regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

	const inputPwd1 = $("#pwd1").val().trim();

	if (regExp.test(inputPwd1)) {
		$("#checkPwd1").text("유효한 비밀번호 형식입니다.").css("color", "green");

		checkObj.pwd1 = true;

	} else {
		$("#checkPwd1").text("비밀번호가 유효하지 않습니다.").css("color", "red");

		checkObj.pwd1 = false;

	}

});

// 비밀번호, 비밀번호 확인 일치 여부 판단
$("#pwd1, #pwd2").on("input", function() {

	const pwd1 = $("#pwd1").val();
	const pwd2 = $("#pwd2").val();

	if (pwd1.trim() == "" && pwd2.trim() == "") { // 둘다 비었을 때
		$("#checkPwd1").html("&nbsp;"); // &nbsp; == 띄어쓰기
		$("#checkPwd2").html("&nbsp;");

		checkObj.pwd2 = false;

	} else if (pwd1 == pwd2) {
		$("#checkPwd2").text("비밀번호가 일치합니다.").css("color", "green");

		checkObj.pwd2 = true;

	} else {
		$("#checkPwd2").text("비밀번호 불일치합니다.").css("color", "red");

		checkObj.pwd2 = false;

	}
});

// 이름 유효성 검사
$("#inputName").on("input", function() {
		const regExp = /^[가-힣]{2,5}$/;
		// 이벤트 핸들러 내부에 작성된 this == 이벤트가 발생한 요소 == $("name")
		const inputName = $(this).val().trim();
		
		if (regExp.test(inputName)) {
			$("#checkName").text("유효한 이름입니다.").css("color", "green");
			
			checkObj.inputName = true;
			
		} else {
			$("#checkName").text("한글 2~5글자 작성하세요.").css("color", "red");
			
			checkObj.inputName = false;
			
		}
	});

// 회원 가입 버튼 클릭 시 전체 유효성 검사 여부 확인
function validate() {

	// checkObj에 작성된 속성들이 모두 true인지 확인

	// 객체 내 속성을 순차접근하는 반복문 : for in 구문
	for (const key in checkObj) {

		if (!checkObj[key]) { // false 값을 가진 속성이 있을 경우

			let msg;

			switch (key) {

				case "email":
					msg = "이메일이 유효하지 않습니다.";
					break;
				case "pwd1":
					msg = "비밀번호가 유효하지 않습니다.";
					break;
				case "pwd2":
					msg = "비밀번호가 일치하지 않습니다. ";
					break;
				case "inputName":
					msg = "이름이 유효하지 않습니다.";
					break;
			}

			// msg에 담긴 내용 출력
			swal(msg).then(function() {

				const selector = "#" + key;

				$(selector).focus();
				// 유효하지 않은 값을 입력한 부분으로 포커스 이동
			});

			return false; // submit 이벤트 제거(회원가입 실행 X)
		}
	}
} 

// 파일 첨부 창
$(function() {
	$(".picture").on("click", function() {
		$("#img").click();
	});
});

// 미리 보기
function LoadImg(value, num) {
	if (value.files && value.files[0]) {
		var reader = new FileReader();

		reader.readAsDataURL(value.files[0]);
		reader.onload = function(e) {

			$(".picture").eq(num).children("img").attr("src", e.target.result);
		}
	}
}
		
// 전화번호 하이픈 자동 추가
function autoHypenPhone(str) {
	str = str.replace(/[^0-9]/g, '');
	var tmp = '';
	if (str.length < 4) {
		return str;
	} else if (str.length < 7) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	} else if (str.length < 11) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	} else {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
	return str;
}
	var Phone = document.getElementById('phone');
	Phone.onkeyup = function(event) {
		event = event || window.event;
		var _val = this.value.trim();
		this.value = autoHypenPhone(_val);
	}

// ------------------------------------------------------------------------------------------------- 로그인 js

	function loginValidate() {
			if ($("#memberEmail").val().trim().length == 0) {
				swal({
					"icon" : "warning",
					"title" : "아이디를 입력해주세요."
				}).then(function() {
					$("#memberEmail").focus();
				});
				return false;
			}

			if ($("#memberPw").val().trim().length == 0) {
				swal({
					"icon" : "warning",
					"title" : "비밀번호를 입력해주세요."
				}).then(function() {
					$("#memberPw").focus();
				});
				return false;
			}
		}
// ------------------------------------------------------------------------------------------------- 회원탈퇴 js
            function secession() {

                swal({
                    title: "회원 탈퇴",
                    text: "정말 탈퇴하시겠습니까?",
                    icon: "info",
                    buttons: [
                        '아니요',
                        '네'
                    ],
                    dangerMode: true,
                }).then(function (isConfirm) {
                    if (isConfirm) {
                        $("#secession").submit();
                    } 
                })
            }