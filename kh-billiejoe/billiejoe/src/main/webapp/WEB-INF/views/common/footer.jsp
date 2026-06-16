<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <footer>
    <div class="container">
      © 2021 billieJoe All Rights Reserved.
    </div>
  </footer>
  <%-- swal 창 --%>
  <c:if test="${!empty title}">
  <script>
    swal({
  	  "icon" : "${icon}",
  		  "title" : "${title}",
  	  "text" : "${text}"
    });
  </script>
  </c:if>
</body>
</html>