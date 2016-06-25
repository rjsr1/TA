
<%@ page import="ta.EvaluationsByCriterion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluationsByCriterion.label', default: 'EvaluationsByCriterion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-evaluationsByCriterion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-evaluationsByCriterion" class="content scaffold-show" role="main">
			<h1>Evaluations in criterion</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list evaluationsByCriterion">
			
				<g:if test="${evaluationsByCriterionInstance?.criterion}">
				<li class="fieldcontain">
					<span id="criterion-label" class="property-label"><g:message code="evaluationsByCriterion.criterion.label" default="Criterion" /></span>
					
						<span class="property-value" aria-labelledby="criterion-label"><g:link controller="criterion" action="show" id="${evaluationsByCriterionInstance?.criterion?.id}">${evaluationsByCriterionInstance?.criterion?.description?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluationsByCriterionInstance?.criterionAverage}">
				<li class="fieldcontain">
					<span id="criterionAverage-label" class="property-label"><g:message code="evaluationsByCriterion.criterionAverage.label" default="Criterion Average" /></span>
					
						<span class="property-value" aria-labelledby="criterionAverage-label"><g:fieldValue bean="${evaluationsByCriterionInstance}" field="criterionAverage"/></span>
					
				</li>
				</g:if>
			
			</ol>

			<h1>Evaluations</h1>

			<table>
				<thead>
				<tr>

					<th>Origin</th>

					<th>Date</th>

					<th>Value</th>

				</tr>
				</thead>
				<tbody>
				<g:each in="${evaluationsByCriterionInstance?.evaluations}" status="i" var="evaluationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: evaluationInstance, field: "origin")}</td>

						<td>${fieldValue(bean: evaluationInstance, field: "applicationDate")}</td>

						<td><g:link name="${evaluationInstance?.value}" controller="evaluation" action="show" id="${evaluationInstance?.id}">${fieldValue(bean: evaluationInstance, field: "value")}</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>

			<g:form url="[resource:evaluationsByCriterionInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<!-- <g:link class="edit" action="edit" resource="${evaluationsByCriterionInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link> -->
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
