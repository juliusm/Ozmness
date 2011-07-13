<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Insert title here</title>
<g:set var="entityName" value="Rate Employee" />
</head>
<body>
	
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list">Ratings List</g:link></span>
        </div>
        <h1>
		${entityName}
	</h1>
	<div class="body">
		<g:form action="save">
			<div class="dialog">
				<table>
                                         <g:hiddenField name="rows" value="${ratingsList.size}"/>
					<tbody>
						<tr>
							<td>Employee:</td>
							<td>
								<g:select name="rated" from="${employeesRatedList}" optionKey="id" value="${name}"  />
							</td>
						</tr>
						<tr>
							<th colspan="4" align="left">Technologies:</th>
						</tr>
						<g:each in="${ratingsList}" status="i" var="ratings">
							<g:hiddenField name="ratings${i}.technology.id" value="${ratings.technology.id}"/>
							<tr class="prop">
								<td valign="top" class="name"><label for="ratings"><g:message
											code="ratings.technology.label"
											default="${ratings.technology.name}" />
								</label></td>
								
								<td valign="top"
									class="name">
									
									<g:select name="ratings${i}.value" from="${1..3}" value="${value}" />
								</td>
								<td><g:textField name="ratings${i}.comment"/></td>
							</tr>
						</g:each>

					</tbody>
				</table>
                               
			</div>
			<div class="buttons">
				<span class="button"><g:submitButton name="create"
						class="save"
						value="${message(code: 'default.button.create.label', default: 'Rate Employee')}" />
				</span>
			</div>
		</g:form>

	</div>

</body>
</html>