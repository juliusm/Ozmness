
<%@ page import="com.orangeandbronze.ozmness.Project_Employee" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'project_Employee.label', default: 'Project_Employee')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'project_Employee.id.label', default: 'Id')}" />
                        
                            <th><g:message code="project_Employee.employee.label" default="Employee" /></th>
                        
                            <th><g:message code="project_Employee.project.label" default="Project" /></th>
                        
                            <g:sortableColumn property="role" title="${message(code: 'project_Employee.role.label', default: 'Role')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${project_EmployeeInstanceList}" status="i" var="project_EmployeeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${project_EmployeeInstance.id}">${fieldValue(bean: project_EmployeeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: project_EmployeeInstance, field: "employee")}</td>
                        
                            <td>${fieldValue(bean: project_EmployeeInstance, field: "project")}</td>
                        
                            <td>${fieldValue(bean: project_EmployeeInstance, field: "role")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${project_EmployeeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
