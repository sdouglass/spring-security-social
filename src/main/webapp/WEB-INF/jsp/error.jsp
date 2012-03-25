<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%! private static final Logger LOGGER = Logger.getLogger("error"); %>
<html>
  <head>
    <title>Error: <%= exception.getMessage() %></title>
  </head>
  <body>
          <p>An error occurred.</p>

          <br> <br>

          <p style="font-weight:bold;"><%= exception.getMessage() %></p>

          <div id="errorDetails" style="display:none;">
            <%
              StringWriter sw = new StringWriter();
              PrintWriter pw = new PrintWriter(sw);
              exception.printStackTrace(pw);
            %>
            <%= sw.toString() %>
          </div>

          <br> <br> <br>

          <%
//            exception.printStackTrace();
            LOGGER.error(exception);
          %>

  </body>
</html>
