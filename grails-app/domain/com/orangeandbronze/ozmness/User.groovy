package com.orangeandbronze.ozmness

class User {

	String username
	String password
        String password2
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
        
        def springSecurityService
    
        static transients = ['password2']

	static constraints = {
		username(blank: false, unique: true)
                password(blank: false, validator: {password, obj ->
                        def password2  = obj.properties['password2']
                        if(password2 == null){return true} 
                        password2 == password ? true : ['invalid.matchingpasswords']
                })
	}
        
        def beforeInsert = {
            password = springSecurityService.encodePassword(password)
            enabled = true
        }

    
        def beforeUpdate = {
            password = springSecurityService.encodePassword(password2)
            password2 = springSecurityService.encodePassword(password2)
            return true
        }
    
	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
}