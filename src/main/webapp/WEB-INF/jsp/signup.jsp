<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Sign Up!</title>
</head>
<body>
<form:form name="regForm" commandName="user" method="post">

    <div id="global_errors">
        <form:errors path="*" cssClass="errors" element="div"/>
    </div>

    <fieldset>
        <legend>Sign Up!</legend>
        <ol>
            <li>
                <label for="username">Username:</label>
                <form:input path="username" id="username" />
            </li>
            <li>
                <label for="password">Password:</label>
                <form:password path="password" id="password"/>
            </li>
            <li>
                <input type="submit" id="submitBtn" name="submitBtn" class="submitBtn" value="Register">
            </li>
        </ol>
    </fieldset>

</form:form>

</body>
</html>