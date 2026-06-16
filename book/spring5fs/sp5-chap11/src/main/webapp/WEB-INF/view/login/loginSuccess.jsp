<%@ taglib prefix="spirng" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kwang
  Date: 2022-06-13
  Time: 오후 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spirng:message code="login.title"/> </title>
</head>
<body>
    <p>
      <spring:message code="login.done"/>
    </p>
    <p>
      <a href="/">[<spring:message code="go.main"/>]</a>

    </p>
</body>
</html>
