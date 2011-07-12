environments {
	development {
		dataSource {
			dbCreate = 'update' // one of 'create', 'create-drop','update'
			driverClassName='com.mysql.jdbc.Driver'
			url = 'jdbc:mysql://localhost/ozmness'
			username = 'root'
			password = 'password'
		}
	}
	
}