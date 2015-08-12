<%@ page import="ta.Question" %>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="question.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="question" required="" value="${questionInstance?.question}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'alternatives', 'error')} ">
	<label for="alternatives">
		<g:message code="question.alternatives.label" default="Alternatives" />
		
	</label>
	<g:select name="alternatives" from="${ta.Alternative.list()}" multiple="multiple" optionKey="id" size="5" value="${questionInstance?.alternatives*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'answer', 'error')} required">
	<label for="answer">
		<g:message code="question.answer.label" default="Answer" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="answer" required="" value="${questionInstance?.answer}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'evaluations', 'error')} ">
	<label for="evaluations">
		<g:message code="question.evaluations.label" default="Evaluations" />
		
	</label>
	

</div>

