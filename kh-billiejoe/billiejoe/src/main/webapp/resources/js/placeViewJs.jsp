<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

const like = '${like}'
const addr = '${place.placeAddr}'
const name = '${place.placeName}'
const price = '${place.placeCharge}'
const priceInt = parseInt(price);

function check() {
	if($("input:checkbox[name='checkbox']:checked").length==0){S
		alert("예약시간을 선택해주세요")
		return false;
	}else{
		return true;
	}
}
	
	
	$(".unLogin").click(function () {
		alert("로그인 후 사용해 주세요")
	})
	$(".same").click(function () {
		alert("본인이 작성한 장소입니다")
	})


function like_btn() {
	            
        $.ajax({
        	url :"likeCheck",
        	type :"POST",
        	data : {"like" : like},
        	success : function (result) {
        		console.log(result)
				if(result == 3){
					alert("로그인 후 사용해 주세요")
				}else{
					location.reload();
				}
			},
			error : function name() {
			}
        })
}

$(function(){
    /* 날짜 선택 관련 스크립트 */
    $("#date3").datepicker({
        onSelect:function(dateText, inst) {
		const selectedMonth = inst.selectedMonth+1
		const selectedDay = inst.selectedDay
            $("#time").children().remove()
            function leftPad(value) { 
            	if (value >= 10) { return value; }
            	return "0"+selectedMonth; 
            	}
            function leftPad2(value) { 
            	if (value >= 10) { return value; }
            	return "0"+selectedDay; 
            	}

           
            const date = inst.selectedYear +'-'+leftPad(selectedMonth)+'-'+leftPad2(selectedDay);
            
            /* 예약일 데이터 넘기기 */
            $("#hidden-day").val(date)
            console.log(date)
            
            $.ajax({
            	url :"reservationCheck",
            	data : {"date" : date},
            	type : "post",
            	success : function(reslut){
                    function check(array,value){
                        return array.some(function(arrayValue){
                            return value==arrayValue;
                        })
                    }
                    
                    for(i=0; i<24; i++){
                        if(check(reslut.time,i)){
                            
                            let inputPhone = $("<input>",{type : "checkbox",id:"check"+i, name : "checkbox", value : i,ONCLICK:"check_all();"})
                           /*  let inputPhone = $("<input type = checkbox id = check"+i+"name=checkbox value = "+i+"onclick = ckeck_all();</input") */
                            let label = $("<label for = check"+i+">"+i+"시"+"</label>")
                            
                            $("#time").append(inputPhone).append(label);


                        }else{
                        	let label = $("<label class='no-checkbox'>"+i+"시"+"</label>")
                            $("#time").append(label);
                        }
                    }
            	},
            	error : function(){}
            });
            
         // 배열에 값이 중복되는지 확인하는 함수
         
         	
        },
        minDate: '-0d'
    });
});



/** 
* 동시에 여러개를 선택 혹은 해제 할 경우 사용. (단, 아래서 위로의 선택 혹은 해제는 안되고 각 체크 박스의 값은 유일한 값이어야 한다.)
* 같은 이름의 체크 박스 객체 배열에서 사용..
* <input type="checkbox" name="체크박스명" onClick="javascript: check_shift();">
* created by anthunt01@edmsoft.com 2005/08/31
**/
var count = 0;
var oldCheckValue = "";    // 기존 클릭한 체크 박스의 값을 저장하기 위한 변수 : 시작점을 구분하기 위함

function check_all() {
   
   var obj = window.event.srcElement;                    // 클릭한 체크 박스 객체
   var oObject = document.getElementsByName(obj.name); // 동일한 이름을 가진 체크 박스 객체 배열
   var vCheck = (obj.checked == true) ? true : false;  // 선택 혹은 해제 여부
   var vBreak = false;                                    // 선택 혹은 해제를 시작/종료 여부
   var input = document.querySelectorAll("input");
    count++
     //  3번째 카운트일때 모든 선택값 취소하고 3번째 선택값을 체크 카운트횟수는 1로 변경 check_shift()
     if(count >=3) {
           $("input").prop('checked', false); 
           oldCheckValue = "";
           vCheck = false;
           vBreak = false;
           count = 1;
           event.target.checked = true;
           // console.log(count)
           oldCheckValue = event.target.value;
       }
    if(oldCheckValue != "") {
        // 체크 박스 객체 갯수만큼 루프
        for(var i = 0; i < oObject.length; i++) {
            // 기존 클릭한 체크 박스값이 존재하는가를 확인하여
            // 값이 없으면 처음부터 적용하고 그렇지 않으면 기존
            // 클릭한 체크 박스 부터 지금 클릭한 체크 박스 까지 선택 혹은 해제를 적용한다.
            if(oldCheckValue == "") {
                if(oObject[i].value == obj.value) break;    // 지금 클릭한 체크 박스의 값과 루프의 체크 박스의 값이 같으면 루프를 빠져나간다.
                else oObject[i].checked = vCheck;            // 값이 다르면 선택/해제를 적용한다.
            }else {
                // 기존 체크 박스의 값과 같으면 선택/해제 여부를 결정하고
                // 적용을 시작한다.
                if(oObject[i].value == oldCheckValue) {
                    vCheck = (oObject[i].checked == true) ? true : false;
                    vBreak = true;
                }
                if(vBreak) {
                    oObject[i].checked = vCheck; 
                }   // 적용이 종료 되지 않았으면 적용한다.
                if(oObject[i].value == obj.value) {
                    break;    // 지금 클릭한 체크 박스의 값과 같으면 적용을 종료 한다.
                }
             }
        }
        
        
        $("#price-zone").children().remove()
        var price = $("<p> 가격</p>");
        var hourlyPrice = $("<p>"+priceInt.toLocaleString()+"원/시간 x "+$("input:checkbox[name='checkbox']:checked").length +" 시간</p>");
        var sumPrice = $("<p id = 'price'>"+ (priceInt*$("input:checkbox[name='checkbox']:checked").length).toLocaleString()+"원</p>");
        $("#price-zone").append(price).append(hourlyPrice).append(sumPrice);
        $("#hidden-price").val(priceInt*$("input:checkbox[name='checkbox']:checked").length)
        
    }else {
        oldCheckValue = obj.value;    // 체크된 값이 없으면 처음 체크된 값을 oldCheckValue 담아준해준다.
    }
    
}



</script>
