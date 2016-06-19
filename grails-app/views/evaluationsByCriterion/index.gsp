
<%@ page import="ta.EvaluationsByCriterion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluationsByCriterion.label', default: 'EvaluationsByCriterion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-evaluationsByCriterion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-evaluationsByCriterion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="evaluationsByCriterion.criterion.label" default="Criterion" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${evaluationsByCriterionInstanceList}" status="i" var="evaluationsByCriterionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${evaluationsByCriterionInstance.id}">${fieldValue(bean: evaluationsByCriterionInstance, field: "criterion")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${evaluationsByCriterionInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
