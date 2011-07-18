package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured;

class HomeController {

	def springSecurityService

	@Secured(['IS_AUTHENTICATED_FULLY'])
	def index = {
            def user = springSecurityService.currentUser
            def admin = Role.findByAuthority('ROLE_ADMIN')
            def dev = Role.findByAuthority('ROLE_DEV')
            if(user.getAuthorities().contains(admin)){
                render(view: "admin")
            }else if(user.getAuthorities().contains(dev)){
                render(view:"dev")
            }
	}
}
