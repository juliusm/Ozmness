package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY'])
class RatingController {
    
    def springSecurityService
    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    
    def index = {
        def currentUser = springSecurityService.currentUser
        def admin = Role.findByAuthority('ROLE_ADMIN')
        def canView = currentUser.getAuthorities().contains(admin)
        if(canView){
            redirect(action: "list", params: params)
        }else{
            redirect(action: "show", id: currentUser.id)
        }
    }

    def list = {
        def currentUser = springSecurityService.currentUser
        def dev = Role.findByAuthority('ROLE_ADMIN')
        def canView = currentUser.getAuthorities().contains(dev)
        if(canView){
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ratingInstanceList: Rating.list(params), ratingInstanceTotal: Rating.count()]
        }else{
            redirect(action: "show", id: currentUser.id)
        }
    }

    @Secured(['ROLE_DEV'])
    def create = {
                def employeesRated = []
                def creator = Employee.get(springSecurityService.principal.id)
                employeesRated.add(creator)
                for(employee in creator.proteges){
                    employeesRated.add(employee)
                }
                for(project in creator.leadProjects){
                    for(member in project.members){
                        employeesRated.add(member)
                    }
                }
                employeesRated.unique()
		def technologies = Technology.list()
		def ratings = []
		for(technology in technologies){
			def ratingInstance = new Rating()
			ratingInstance.properties = params
			ratingInstance.technology = technology
			ratings.add(ratingInstance)
			}
        return [ratingsList: ratings, employeesRatedList: employeesRated]
    }

    @Secured(['ROLE_DEV'])
    def save = {

        int rows = Integer.parseInt(params.rows)-1
        def creator = Employee.get(springSecurityService.principal.id)
        def rated = Employee.get(params.rated)
        
        for(i in 0..rows){
          def ratingInstance = new Rating(params."ratings${i}")
          def existingRating = Rating.find("from Rating where creator = ? and rated = ? and technology= ?",[creator, rated, ratingInstance.technology])
     
          boolean save = false
           if(existingRating){
               existingRating.properties = params."ratings${i}"
               save = existingRating.save(flush: true)
           }else{
               ratingInstance.rated = rated
               ratingInstance.creator = creator
               save = ratingInstance.save(flush: true)
           }
            if (!save) {
                 render(view: "create")
            }
        }
            redirect(action: "list")
    }

    def show = {
        def rated = Employee.get(params.id)
        def ratings = Rating.findAllByRated(rated)
        
        def currentUser = springSecurityService.currentUser
        def dev = Role.findByAuthority('ROLE_DEV')
        def canRate = currentUser.getAuthorities().contains(dev)
        println currentUser.getAuthorities()
        def creator = []
       
        if (!ratings && !canRate) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
        else {
             for(employee in ratings.toList()){
                creator.add(employee.creator)
            }
            creator.unique()
            [ratingsList: ratings.toList(), creatorList: creator, rated: rated, canRate: canRate]
        }
    }

    @Secured(['ROLE_DEV'])
    def edit = {
        def ratingInstance = Rating.get(params.id)
        if (!ratingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [ratingInstance: ratingInstance]
        }
    }


}
