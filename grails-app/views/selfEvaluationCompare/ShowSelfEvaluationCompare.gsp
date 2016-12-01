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
            <th id=${student.name} >${student.name}</th>
            <g:each in="${student.criteriaAndEvaluations}" var="eval">
                <g:each in="${student.selfEvaluationsByCriterion}" var="self">

                    <g:if test="${eval.criterion.description==self.criterion.description}">


                        <g:if test="${eval.criterionAverage<self.criterionAverage}">
                            <th style="color: #cc0000">${eval.criterionAverage}|${self.criterionAverage}</th>
                        </g:if>
                        <g:else>
                            <th style="color: #006dba">${eval.criterionAverage}|${self.criterionAverage}</th>
                        </g:else>
                    </g:if>

                </g:each>
            </g:each>
        </tr>


    </g:each>


</table>
</body>
</html>