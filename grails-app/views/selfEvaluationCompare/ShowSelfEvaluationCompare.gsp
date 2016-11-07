<%--
  Created by IntelliJ IDEA.
  User: ess
  Date: 06/11/16
  Time: 22:43
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Self-Evaluation Compare Table</title>
</head>

<body>
<g:each in="studentList" var="student" status="i">
    <tr>${student}</tr>
    <tr>    </tr>



</g:each>

</body>
</html>