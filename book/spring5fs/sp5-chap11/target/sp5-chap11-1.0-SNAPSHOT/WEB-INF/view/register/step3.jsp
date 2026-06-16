<%--
  Created by IntelliJ IDEA.
  User: kwang
  Date: 2022-06-09
  Time: 오후 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="member.register"/></title>
</head>
<body>
    <p><spring:message code="register.done" arguments="${formData.name}"/></p>
    <p><a href="/">[<spring:message code="go.main"/>]</a></p>
</body>
</html>
