package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured;

class RatingController {
    
    def springSecurityService
    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ratingInstanceList: Rating.list(params), ratingInstanceTotal: Rating.count()]
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
        Employee rated = Employee.get(2)
        def ratings = Rating.findByRated(Employee.get(params.id))
        def creator = []
       
        if (!ratings) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
        else {
             for(employee in ratings.list()){
                creator.add(employee.creator)
            }
            creator.unique()
            [ratingsList: ratings.list(), creatorList: creator]
        }
    }

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

    def update = {
        def ratingInstance = Rating.get(params.id)
        if (ratingInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (ratingInstance.version > version) {
                    
                    ratingInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'rating.label', default: 'Rating')] as Object[], "Another user has updated this Rating while you were editing")
                    render(view: "edit", model: [ratingInstance: ratingInstance])
                    return
                }
            }
            ratingInstance.properties = params
            if (!ratingInstance.hasErrors() && ratingInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'rating.label', default: 'Rating'), ratingInstance.id])}"
                redirect(action: "show", id: ratingInstance.id)
            }
            else {
                render(view: "edit", model: [ratingInstance: ratingInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def ratingInstance = Rating.get(params.id)
        if (ratingInstance) {
            try {
                ratingInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
    }
}
