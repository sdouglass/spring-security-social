<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>Twitter Account Connection Status</title>
  </head>
  <body>

          <h3>You do not have a Twitter account connected to your account.</h3>

          <br><br>

          <form name="twitter" action="<c:url value="/connect/twitter"/>" method="post">
            <input type="submit" name="submitBtn" value="Connect With Twitter!">
          </form>

          <p><a href="<c:url value="/"/>">Back</a></p>
  </body>
</html>


