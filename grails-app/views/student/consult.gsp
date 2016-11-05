<%--
  Created by IntelliJ IDEA.
  User: joao
  Date: 19/05/16
  Time: 17:57
--%>

<%@ page import="ta.Student" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-student" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list student">

                <g:if test="${studentInstance?.login}">
                    <li class="fieldcontain">
                        <span id="login-label" class="property-label"><g:message code="student.login.label" default="Login" /></span>

                        <span class="property-value" aria-labelledby="login-label"><g:fieldValue bean="${studentInstance}" field="login"/></span>

                    </li>
                </g:if>

                <g:if test="${studentInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="student.name.label" default="Name" /></span>

                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${studentInstance}" field="name"/></span>

                    </li>
                </g:if>

                <g:if test="${studentInstance?.evaluations}">
                    <li class="fieldcontain">
                        <span id="evaluations-label" class="property-label"><g:message code="student.evaluations.label" default="Evaluations" /></span>

                        <span class="property-value" aria-labelledby="evaluations-label"><g:fieldValue bean="${studentInstance}" field="evaluations"/></span>

                    </li>
                </g:if>

            </ol>
            <g:form url="[resource:studentInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${studentInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>