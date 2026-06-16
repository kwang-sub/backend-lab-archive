// ------------------------------------------------------------------------------------------------------ 회원가입 js

// 각 유효성 검사 결과를 저장할 객체
const checkObj = {
	
	"name": false
};


// 이름 유효성 검사
$("#memberName").on("input", function() {
		const regExp = /^[가-힣]{2,5}$/;
		// 이벤트 핸들러 내부에 작성된 this == 이벤트가 발생한 요소 == $("name")
		const inputName = $(this).val().trim();
		
		if (regExp.test(inputName)) {
			$("#checkName").text("유효한 이름입니다.").css("color", "green");
			
			checkObj.name = true;
			
		} else {
			$("#checkName").text("한글 2~5글자 작성하세요.").css("color", "red");
			
			checkObj.name = false;
			
		}
	});

// 회원 가입 버튼 클릭 시 전체 유효성 검사 여부 확인
function updateValidate() {

	// checkObj에 작성된 속성들이 모두 true인지 확인

	// 객체 내 속성을 순차접근하는 반복문 : for in 구문
	for (const key in checkObj) {

		if (!checkObj[key]) { // false 값을 가진 속성이 있을 경우

			let msg;

			switch (key) {

			
				case "name":
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



// ------------------------------------------------------------------------------------------------- 로그인 js

	