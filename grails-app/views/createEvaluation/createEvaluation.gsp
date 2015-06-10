<%@ page import="ta.EvaluationController" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Criar Avaliacao</title>
</head>
<body>

	<div id="messageBox">
		<p id="messageBoxText">${pageMessage}</p>
	</div>

	<g:form name="createEvaluation" action="rippenEvaluation">
		<input name="title" />
		<input name="question description" />
		<input name="question answer" />
		<input name="question alternative" />
		<g:submitButton name="register" value="Register" id="Register"/>
	</g:form>
</body>
</html>
<%@  %>