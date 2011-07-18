package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY'])
class RatingController {
    
    def scaffold = true
    
    def springSecurityService
    
    def RatingService
    
    def list = {
        def currentUser = springSecurityService.currentUser
        def dev = Role.findByAuthority('ROLE_DEV')
        if(currentUser.getAuthorities().contains(dev)){
            redirect(action: "show", id:currentUser.id)
        }
    }

    @Secured(['ROLE_DEV'])
    def create = {
        return [ratingsList: RatingService.ratingInstanceList(), employeesRatedList: RatingService.ratedList()]
    }

    @Secured(['ROLE_DEV'])
    def save = {
        RatingService.storeRatings(params)
        redirect(action: "list")
    }

    def show = {
       
       def rated = Employee.get(params.id)
       def ratings = RatingService.ratingsList(rated)
       def admin = Role.findByAuthority('ROLE_ADMIN')
       def currentUser = springSecurityService.currentUser
      
        if (!ratings && currentUser.getAuthorities().contains(admin)) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])}"
            redirect(action: "list")
        }
        else{
            def creator = RatingService.creatorList(ratings)
            [ratingsList: ratings.toList(), creatorList: creator.toList(), rated: rated]
        }
    }


}
