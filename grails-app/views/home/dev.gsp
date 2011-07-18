<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Insert title here</title>
</head>
<body>
	<div class="nav">
		<span class="menuButton"><a class="home"
			href="${createLink(uri: '/')}"><g:message
					code="default.home.label" />
		</a>
		</span> <span class="menuButton">
			<g:link controller="employee">Employees</g:link>
		</span>
		<span class="menuButton"><g:link controller="project">Projects</g:link></span>
		<span class="menuButton"><g:link controller="rating">Ratings</g:link></span>
	</div>
	<div id="pageBody">
         
            <h1>Welcome!</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
	</div>
        
           
</body>
</html>