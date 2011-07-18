package com.orangeandbronze.ozmness

class RatingService {

    static transactional = true
    
    def springSecurityService

    def ratedList() {
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
        return employeesRated
    }
    
    def ratingInstanceList(params){
        def technologies = Technology.list()
        def ratings = []
        for(technology in technologies){
            def ratingInstance = new Rating()
            ratingInstance.properties = params
            ratingInstance.technology = technology
            ratings.add(ratingInstance)
        }
        return ratings
    }
    
    def storeRatings(params){
        int rows = Integer.parseInt(params.rows)-1
        def creator = Employee.get(springSecurityService.principal.id)
        def rated = Employee.get(params.rated)
        
        for(i in 0..rows){
          def ratingInstance = new Rating(params."ratings${i}")
          def existingRating = Rating.find("from Rating where creator = ? and rated = ? and technology= ?",[creator, rated, ratingInstance.technology])
     
           if(existingRating){
               existingRating.properties = params."ratings${i}"
               existingRating.save(flush: true)
           }else{
               ratingInstance.rated = rated
               ratingInstance.creator = creator
               ratingInstance.save(flush: true)
           }
           
        }
    }
    
    def ratingsList(rated){
        def ratings = []
        if(rated){
        ratings = Rating.findAllByRated(rated)
        }
        return ratings
    }
    
    def creatorList(ratings){
        
        def creator = []
        if(ratings){
         for(employee in ratings.toList()){
                creator.add(employee.creator)
            }
            creator.unique()
        }
        return creator
    }

}
