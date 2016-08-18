
<%@ page import="ta.Report" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-report" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-report" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list report">
			
				<g:if test="${reportInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="report.name.label" default="Name" /></span>
					
						<span id = "n" class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${reportInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="report.tipo.label" default="Tipo" /></span>
					
						<span id="t" class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${reportInstance}" field="tipo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.avaliacao}">
				<li class="fieldcontain">
					<span id="avaliacao-label" class="property-label"><g:message code="report.avaliacao.label" default="Avaliacao" /></span>
					
						<span id="a" class="property-value" aria-labelledby="avaliacao-label"><g:fieldValue bean="${reportInstance}" field="avaliacao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.valor}">
				<li class="fieldcontain">
					<span id="valor-label" class="property-label"><g:message code="report.valor.label" default="Valor" /></span>
					
						<span id="v" class="property-value" aria-labelledby="valor-label"><g:fieldValue bean="${reportInstance}" field="valor"/></span>
					
				</li>
				</g:if>
			
			</ol>

			<table>
				<thead>
				<tr>

					<g:sortableColumn property="name" title="${message(code: 'student.name.label', default: 'Name')}" />

					<g:sortableColumn property="login" title="${message(code: 'student.login.label', default: 'Login')}" />


				</tr>
				</thead>
				<tbody>
				<g:each in="${reportInstance?.students}" status="i" var="studentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${studentInstance.id}">${fieldValue(bean: studentInstance, field: "name")}</g:link></td>

						<td>${fieldValue(bean: studentInstance, field: "login")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>

			<g:form url="[resource:reportInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${reportInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
