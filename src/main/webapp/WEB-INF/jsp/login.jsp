<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Log In!</title>
</head>
<body>

<c:if test="${param['error'] eq 'multiple_users'}">
    <h3>Uh oh! You have multiple accounts associated with your Twitter/Facebook account.</h3>
    <b>You must now sign in with your local username/password.</b>
    <br><br>
</c:if>

<form name="loginForm" action="<c:url value="/j_spring_security_check"/>" method="post">

    <c:if test="${not empty param.loginError}">
        <div id="errorsDiv">
            <div class="errors">
                Login and/or Password incorrect, please try again.<br>
            </div>
        </div>
    </c:if>

    <fieldset>
        <legend>Enter your login and password below:</legend>
        <ol>
            <li>
                <label for="j_username">Login:</label>
                <%--<form:input path="username" id="username" />--%>
                <input type="text" id="j_username" name="j_username"
                       <c:if test="${not empty param.loginError}">value='<c:out value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/>'
                </c:if>>
                <%--<%= request.getSession().getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>--%>
            </li>
            <li>
                <label for="j_password">Password:</label>
                <input type="password" id="j_password" name="j_password">
                <%--<form:input path="displayName" id="displayName"/>--%>
            </li>
            <li>
                <label for="_spring_security_remember_me">Remember my login:</label>
                <input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me"
                       value="true" style="width:auto" checked="checked">
            </li>
            <li>
                <label for="submitBtn">&nbsp;</label>
                <input type="submit" id="submitBtn" name="submitBtn" class="submitBtn" value="Log In!">
            </li>
        </ol>
    </fieldset>

</form>

<br><br>

<fieldset>
    <legend>Log In With Twitter and Facebook</legend>
    <ol>
        <li>
            <form id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST">
                <a href="javascript:document.forms.tw_signin.submit()" title="Log In With Twitter">Log In With
                    Twitter</a>
            </form>
        </li>
        <li>
            <form id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
                <a href="javascript:document.forms.fb_signin.submit()" title="Log In With Facebook">Log In With
                    Facebook</a>
            </form>
        </li>
    </ol>
</fieldset>

<h3><a href="<c:url value="/signup"/>">Sign Up!</a></h3>

</body>
</html>