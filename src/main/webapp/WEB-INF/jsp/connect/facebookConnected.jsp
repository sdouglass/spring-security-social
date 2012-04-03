<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>Facebook Account Connection Status</title>
</head>
<body>

<c:if test="${requestScope['social.addConnection.duplicate'] ne null}">
    <h3 style="color:red">You have already connected a different Facebook account.</h3>
</c:if>

<h3>Your Facebook account is connected to your account!</h3>

<p>If you would like to disconnect your Facebook account click this button:</p>

<form name="facebook" action="<c:url value="/connect/facebook"/>" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" name="submitBtn" value="Disconnect From Facebook">
</form>

<p><a href="<c:url value="/"/>">Back</a></p>
</body>
</html>


