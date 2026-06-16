<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
	 <script>
      $(document).ready(function(){
          $(".del-btn").on("click",function(){
              console.log($(event.target))
              var number = $(event.target).children().val();
              $("#modal-chat-no").val(number);
          })
      }) 
  </script>
