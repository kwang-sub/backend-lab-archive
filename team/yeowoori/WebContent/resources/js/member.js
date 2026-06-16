// 회원 가입 유효성 검사

// 각 유효성 검사 결과를 저장할 객체
const checkObj = {
	"id": false,
	"pwd1": false,
	"pwd2": false,
	"nickName": false
	//"email": false
};


// 아이디가 입력될 때 마다 유효성 검사
// 조건 : 영어, 숫자 6~14글자

$("#id").on("input", function(){

    // 정규표현식 객체 생성
    const regExp = /^[a-zA-Z0-9]{6,12}$/;

    // 입력된 아이디(양쪽 공백 제거)를 얻어와 inputId 변수에 저장
    const inputId = $("#id").val().trim();

    // 입력된 아이디가 정규식에 일치하는 경우 == 유효한 값인 경우
    if(regExp.test(inputId)){
        $("#checkId").text("유효한 아이디 입니다.").css("color", "green");
 		checkObj.id = true;
    } else{
        $("#checkId").text("영어, 숫자를 조합하여 6~12자리").css("color", "red");
 		checkObj.id = false;
    }
});

// 닉네임 유효성 검사
// 조건 : 한글 두 글자 이상 5글자 이하 ->  /^[가-힣]{2,5}$/;
$("#nickName").on("input",function(){

    const regExp =  /^[가-힣]{2,15}$/;

    // 이벤트 핸들러 내부에 작성된 this == 이벤트가 발생한 요소 == $("#name")
    const inputName = $(this).val().trim();

    if(regExp.test(inputName)){
        $("#checkName").text("유효한 닉네임 입니다.").css("color", "green");
        checkObj.nickName = true;

    }else{
        $("#checkName").text("한글 2~15자리").css("color","red");
        checkObj.nickName = false;

    }
});



// 비밀번호 유효성 검사
// 조건 : 영어, 숫자, 특수문자(#, -, _) 6~20글자

$("#pwd1").on("input",function(){
	
    const regExp = /^[a-zA-Z0-9_#-]{6,20}$/
    const inputPw = $(this).val().trim();


    if(regExp.test(inputPw)){
        $("#checkPwd1").text("유효한 비밀번호 입니다.").css("color", "green");
        checkObj.pwd1 = true;

    }else{
        $("#checkPwd1").text("유효하지 않은 비밀번호 입니다.").css("color", "red");
        checkObj.pwd1 = false;

    }
});

// 비밀번호, 비밀번호 확인 일치 여부 판단
// 조건 : 영어, 숫자, 특수문자 (#,-,_)

    $("#pwd1, #pwd2").on("input", function(){

        const inputPwd1 = $("#pwd1").val();

        const inputPwd2 = $("#pwd2").val();

        if(inputPwd1.trim() == "" && inputPwd2.trim() == ""){ // 둘다 비었을 때
            $("checkPwd1").html("&nbsp;");
            $("checkPwd2").html("&nbsp;");

            checkObj.pwd2 = false;

        } else if(inputPwd1 == inputPwd2){
            $("#checkPwd2").text("비밀번호가 일치합니다.").css("color", "green");
            checkObj.pwd2 = true;

        } else{
            $("#checkPwd2").text("비밀번호가 일치하지 않습니다.").css("color", "red");
            checkObj.pwd2 = false;

        }

    });

// 이메일 유효성 검사
// 조건 : 아이디 4글자 이상, 이메일 형식  ->   /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
$("#email1").on("input", function(){
    const regExp = /^[\w]{4,}$/;

    // 이벤트 핸들러 내부에 작성된 this == 이벤트가 발생한 요소 == $("#name")
    const inputEmail = $(this).val().trim();

    if(regExp.test(inputEmail)){
        $("#checkEmail").text("유효한 이메일 형식입니다.").css("color", "green");

        checkObj.email = true;

    }else{
        $("#checkEmail").text("이메일 형식이 올바르지 않습니다.").css("color", "red");

        checkObj.email = false;
    }

});


// 이메일 유효성 검사
// 조건 : 아이디 4글자 이상, 이메일 형식  ->   /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
$("#email2").on("input", function(){
    const regExp = /^[\w]+(\.[\w]+){1,3}$/;

    // 이벤트 핸들러 내부에 작성된 this == 이벤트가 발생한 요소 == $("#name")
    const inputEmail = $(this).val().trim();

    if(regExp.test(inputEmail)){
        $("#checkEmail").text("유효한 이메일 형식입니다.").css("color", "green");

        checkObj.email = true;

    }else{
        $("#checkEmail").text("이메일 형식이 올바르지 않습니다.").css("color", "red");

        checkObj.email = false;
    }

});

    // 회원 가입 버튼 클릭 시 전체 유효성 검사 여부 확인
function validate(){

    // 아이디 중복 검사를 진행했는지 확인
    // * input 태그 값을 모두 String으로 반환됨!
    if( $("#idDup").val() != "true" ){ // 중복 검사를 안한 경우
        swal("아이디 중복 검사를 진행해 주세요.").then(function(){
            $("#idDupCheck").focus(); // 중복 검사 버튼으로 포커스 이동
        });

        return false; // submit 이벤트 제거
    }

   // 닉네임 중복 검사를 진행했는지 확인
    // * input 태그 값을 모두 String으로 반환됨!
    if( $("#nickDup").val() != "true" ){ // 중복 검사를 안한 경우
        swal("닉네임 중복 검사를 진행해 주세요.").then(function(){
            $("#nickDupCheck").focus(); // 중복 검사 버튼으로 포커스 이동
        });

        return false; // submit 이벤트 제거
    }
    // checkObj에 작성된 속성들이 모두 true인지 확인

	console.log(checkObj)


    // 객체 내 속성을 순차접근 하는 반복문 : for in 구문
    for(const key in checkObj){

		console.log("key : " + key);
		console.log(checkObj[key]);
		
        if(!checkObj[key]){ // false 값을 가진 속성이 있을 경우

            let msg;
			switch (key) {
			case "id":
				msg = "아이디가 유효하지 않습니다.";
				break;
			case "pwd1":
				msg = "비밀번호가 유효하지 않습니다.";
				break;
			case "pwd2":
				msg = "비밀번호가 일치하지 않습니다. ";
				break;
			case "nickName":
				msg = "닉네임이 유효하지 않습니다.";
				break;
			}

            // msg에 담긴 내용 출력
            swal(msg).then(function(){

                const selector = "#" + key;

                $(selector).focus();
                // 유효하지 않은 값을 입력한 부분으로 포커스 이동

            });
            
            return false; // submit 이벤트 제거(회원가입 실행 X)
	    }
	}
}
