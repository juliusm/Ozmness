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
                 <p>Congratulations, you have successfully started your first Grails application! At the moment
            this is the default page, feel free to modify it to either redirect to a controller or display whatever
            content you may choose. Above is a list of controllers that are currently deployed in this application,
            click on each to execute its default action:</p>
	</div>
        
           
</body>
</html>