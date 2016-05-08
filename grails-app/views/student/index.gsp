
<%@ page import="ta.Criterion; ta.Student" %>
<%@ page import="ta.Criterion" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-student" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="login" title="${message(code: 'student.login.label', default: 'Login')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'student.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="evaluations" title="${message(code: 'student.evaluations.label', default: 'Evaluations')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${studentInstanceList}" status="i" var="studentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${studentInstance.id}">${fieldValue(bean: studentInstance, field: "login")}</g:link></td>
					
						<td>${fieldValue(bean: studentInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: studentInstance, field: "evaluations")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${studentInstanceCount ?: 0}" />
			</div>
		</div>
	<!--Eu que fiz essa parte-->
	<div>
		<table>
			<thead>
			<tr>

				<g:sortableColumn property="login" title="${message(code: 'student.login.label', default: 'Login')}" />

				<g:sortableColumn property="name" title="${message(code: 'student.name.label', default: 'Name')}" />

				<g:each in="${Criterion.findAll()}" var="evCriterion">
					<g:sortableColumn property="evaluations" title="${evCriterion.name}" />
				</g:each>
			</tr>
			</thead>
			<tbody>
			<g:each in="${studentInstanceList}" status="i" var="studentInstance">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

					<td><g:link action="show" id="${studentInstance.id}">${fieldValue(bean: studentInstance, field: "login")}</g:link></td>

					<td>${fieldValue(bean: studentInstance, field: "name")}</td>

					<g:each in="${ta.Criterion.findAll()}" var="evCriterion">
						<td>
							<g:select id="${studentInstance.login}+/+${evCriterion.name}" name="selector"
									  from="${ta.Student.Concept.CONCEPTS}"
									  value="${studentInstance.evaluations.get(evCriterion.name)}"
									  onchange="${remoteFunction(
											  action: 'updateConcepts',
											  params:'\'studentCriterion=\' + this.id + \'&concept=\' + this.value'
									  )}"
							/>
						</td>
					</g:each>
				</tr>
			</g:each>
			</tbody>
		</table>
	</div>
	</body>
</html>