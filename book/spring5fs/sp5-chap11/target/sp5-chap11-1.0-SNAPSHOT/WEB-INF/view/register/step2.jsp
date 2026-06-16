<%--
  Created by IntelliJ IDEA.
  User: kwang
  Date: 2022-06-09
  Time: 오후 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="member.register"/></title>
</head>
<body>
<h2><spring:message code="member.info"/></h2>
<form:form action="step3" modelAttribute="formData">
    <p>
        <label><spring:message code="email"/>:<br>
            <form:input path="email"/>
            <form:errors path="email" cssStyle="color: red"/>
        </label>
    </p>
    <p>
        <label><spring:message code="name"/>:<br>
            <form:input path="name"/>
            <form:errors path="name"/>
        </label>
    </p>
    <p>
        <label><spring:message code="password"/>:<br>
            <form:password path="password"/>
            <form:errors path="password"/>
        </label>
    </p>
    <p>
        <label><spring:message code="password.confirm"/>:<br>
            <form:password path="confirmPassword"/>
            <form:errors path="confirmPassword"/>
        </label>
    </p>
    <p>
        <input type="submit" value="<spring:message code="register.btn"/>">
    </p>
</form:form>
</body>
</html>
