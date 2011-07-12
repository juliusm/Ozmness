dataSource {
	dialect = org.hibernate.dialect.MySQLInnoDBDialect.class
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
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
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}
