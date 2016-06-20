<%@ page import="ta.Evaluation" %>



<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'origin', 'error')} required">
	<label for="origin">
		<g:message code="evaluation.origin.label" default="Origin" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="origin" from="${evaluationInstance.constraints.origin.inList}" required="" value="${evaluationInstance?.origin}" valueMessagePrefix="evaluation.origin"/>

</div>

<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'criterion', 'error')} required">
	<label for="criterion">
		<g:message code="evaluation.criterion.label" default="Criterion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="criterion" name="criterion.id" from="${ta.Criterion.list()}" optionKey="id" required="" optionValue="${{it.description}}" value="${evaluationInstance?.criterion?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'applicationDate', 'error')} required">
	<label for="applicationDate">
		<g:message code="evaluation.applicationDate.label" default="Application Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="applicationDate" precision="day"  value="${evaluationInstance?.applicationDate}"  />

</div>

<div id="list-student" class="content scaffold-list" role="main">
	<h1><g:message code="default.list.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<table>
		<thead>
		<tr>

			<g:sortableColumn property="name" title="${message(code: 'evaluation.origin.label', default: 'Name')}" />

			<g:sortableColumn property="login" title="${message(code: 'evaluation.value.label', default: 'Login')}" />

			<g:sortableColumn property="value" title="${message(code: 'evaluation.value.label', default: 'Value')}" />

		</tr>
		</thead>
		<tbody>
		<g:each in="${ta.Student.list()}" status="i" var="studentInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

				<td><g:link action="show" id="${studentInstance.id}">${fieldValue(bean: studentInstance, field: "name")}</g:link></td>

				<td>${fieldValue(bean: studentInstance, field: "login")}</td>

				<td>

					<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'value', 'error')} required">
						<label for="value">
							<g:message code="evaluation.value.label" default="Value" />
							<span class="required-indicator">*</span>
						</label>
						<g:select name="value" from="${evaluationInstance.constraints.value.inList}" required="" value="${evaluationInstance?.value}" valueMessagePrefix="evaluation.value"/>

					</div>

				</td>

			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${evaluationInstanceCount ?: 0}" />
	</div>
</div>