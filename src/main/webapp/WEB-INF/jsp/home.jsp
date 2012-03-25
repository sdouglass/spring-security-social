<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>You Are Logged In!</title>
</head>
<body>
<h1>Congratulations!</h1>

<p>You are now logged in.</p>

<p>Your password is: <b>${user.password}</b></p>

<fieldset>
    <legend>Connected Accounts</legend>
    <ol>
        <li>
            <c:choose>
                <c:when test="${twitterConnected}">
                    <form name="twitter" action="<c:url value="/connect/twitter"/>" method="POST">
                    <input type="hidden" name="_method" value="delete">
                    <label for="disconnectTwitter" style="color:green">Twitter Account Connected!</label>
                    <input id="disconnectTwitter" class="submitBtn" type="button" name="disconnectTwitterBtn"
                           value="Disconnect" onclick="document.forms.twitter.submit()">
                    </form>
                </c:when>
                <c:otherwise>
                    <form name="twitter" action="<c:url value="/connect/twitter"/>" method="POST">
                    <label for="connectTwitter">Twitter Account not connected</label>
                    <input id="connectTwitter" class="submitBtn" type="button" name="connectTwitterBtn"
                           value="Connect With Twitter!" onclick="document.forms.twitter.submit()">
                    </form>
                </c:otherwise>
            </c:choose>
        </li>
        <li>
            <c:choose>
                <c:when test="${facebookConnected}">
                    <form name="facebook" action="<c:url value="/connect/facebook"/>" method="POST">
                    <label for="disconnectFacebook" style="color:green">Facebook Account Connected!</label>
                    <input id="disconnectFacebook" class="submitBtn" type="button" name="disconnectFacebookBtn"
                           value="Disconnect" onclick="document.forms.facebook.submit()">
                    </form>
                </c:when>
                <c:otherwise>
                    <form name="facebook" action="<c:url value="/connect/facebook"/>" method="POST">
                    <label for="connectFacebook">Facebook Account not connected</label>
                    <input id="connectFacebook" class="submitBtn" type="button" name="connectFacebookBtn"
                           value="Connect With Facebook!" onclick="document.forms.facebook.submit()">
                    </form>
                </c:otherwise>
            </c:choose>
        </li>
    </ol>
</fieldset>

<h3><a href="<c:url value="/logout"/>">Log Out</a></h3>
</body>
</html>