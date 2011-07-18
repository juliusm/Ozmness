package com.orangeandbronze.ozmness

class ProjectService {

    static transactional = true

    def springSecurityService
    
    def leaderList() {
         def currentUser = springSecurityService.currentUser
         def admin = Role.findByAuthority('ROLE_ADMIN')
         def canChooseLeader = currentUser.getAuthorities().contains(admin)
         def leaderList = []
         if(canChooseLeader){
            leaderList = Employee.list()
         }else{
            leaderList.add(Employee.get(currentUser.id))
        }
        return leaderList
    }
    
    def canModify(project){
        def currentUser = springSecurityService.currentUser
        def admin = Role.findByAuthority('ROLE_ADMIN')
        if(project.lead.id == currentUser.id || currentUser.getAuthorities().contains(admin)){
            return true
        }
        return false
    }
}
