package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured;

class HomeController {

	def springSecurityService
	
    def index = {
		def user = springSecurityService.currentUser
//		switch(user){
//			case()
//			}
		}
}
