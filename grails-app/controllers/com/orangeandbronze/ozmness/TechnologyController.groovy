package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class TechnologyController {

    def scaffold = true
   
}
