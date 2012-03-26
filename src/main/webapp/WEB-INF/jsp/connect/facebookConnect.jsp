<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>Facebook Account Connection Status</title>
</head>
<body>

<c:if test="${requestScope['social.addConnection.duplicate'] ne null}">
    <h3 style="color:red">The Facebook account you tried to use is already connected to another account.</h3>
</c:if>

<h3>You do not have a Facebook account connected to your account.</h3>

<form name="facebook" action="<c:url value="/connect/facebook"/>" method="post">
    <input type="submit" name="submitBtn" value="Connect With Facebook!">
</form>

<p><a href="<c:url value="/"/>">Back</a></p>
</body>
</html>


