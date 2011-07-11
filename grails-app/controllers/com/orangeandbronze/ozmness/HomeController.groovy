package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured;

class HomeController {

	def springSecurityService

	@Secured(['IS_AUTHENTICATED_FULLY'])
	def index = {
		def user = springSecurityService.authentication

		for (auth in user.authorities) {
			if (auth.authority == 'ROLE_ADMIN') {
				render(view: "admin")
			}else if(auth.authority == 'ROLE_DEV'){
				render(view:"dev")
			}
		}

	}

}
