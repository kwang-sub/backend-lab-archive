<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <script >
    	var placeName = '${place.placeName}'
    	var price = '${sumPrice}'
		var name = '${loginMember.memberName}'
		var phone = '${loginMember.memberPhone}'
		var emaile = '${loginMember.memberEmail}'
    function iamport(){
		//가맹점 식별코드
		IMP.init('imp04401960');
		IMP.request_pay({
		    pg : 'kakaopay',
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : placeName , //결제창에서 보여질 이름
		    amount : price, //실제 결제되는 가격
		    buyer_email : emaile,
		    buyer_name : name,
		    buyer_tel : phone,
		}, function(rsp) {
			console.log(rsp);
		    if ( rsp.success ) {
		    	$("#merchantUid").val(rsp.merchant_uid);
		    	$("#sub").submit();
		    	
		    	/* var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num; */
		    } else {
		    	 var msg = '결제에 실패하였습니다.';
		         msg += '에러내용 : ' + rsp.error_msg;
			   	 alert(msg); 
		    }
		});
	}
    
    </script>
