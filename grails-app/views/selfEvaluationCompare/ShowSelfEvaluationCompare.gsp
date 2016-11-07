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
<table style="width:100%">
    <tr>
        <th>    </th>
        <g:each in="${studentListInstance[0].criteriaAndEvaluations}" var="EvalByCriterion">
            <th>${EvalByCriterion.criterion.description}</th>
        </g:each>
    </tr>
    <tr>
        <th>    </th>
        <g:each in="${studentListInstance[0].criteriaAndEvaluations}" var="index">
        <th>A|AA</th>
    </g:each>
    </tr>
    <g:each in="${studentListInstance}" var="student">
        <tr>
            <th>${student.name}</th>
            <g:each in="${student.criteriaAndEvaluations}" var="eval">
            <th> ${eval.criterionAverage}|${eval.criterionAverage}</th>
        </g:each>
        </tr>


    </g:each>


</table>


</body>
</html>