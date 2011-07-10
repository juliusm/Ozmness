

<%@ page import="com.orangeandbronze.ozmness.Project_Employee" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'project_Employee.label', default: 'Project_Employee')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${project_EmployeeInstance}">
            <div class="errors">
                <g:renderErrors bean="${project_EmployeeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="employee"><g:message code="project_Employee.employee.label" default="Employee" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: project_EmployeeInstance, field: 'employee', 'errors')}">
                                    <g:select name="employee.id" from="${com.orangeandbronze.ozmness.Employee.list()}" optionKey="id" value="${project_EmployeeInstance?.employee?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="project"><g:message code="project_Employee.project.label" default="Project" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: project_EmployeeInstance, field: 'project', 'errors')}">
                                    <g:select name="project.id" from="${com.orangeandbronze.ozmness.Project.list()}" optionKey="id" value="${project_EmployeeInstance?.project?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role"><g:message code="project_Employee.role.label" default="Role" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: project_EmployeeInstance, field: 'role', 'errors')}">
                                    <g:select name="role" from="${com.orangeandbronze.ozmness.ProjectRole?.values()}" keys="${com.orangeandbronze.ozmness.ProjectRole?.values()*.name()}" value="${project_EmployeeInstance?.role?.name()}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
