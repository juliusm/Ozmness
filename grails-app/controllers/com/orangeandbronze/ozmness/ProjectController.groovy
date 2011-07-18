package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY'])
class ProjectController {

    def ProjectService
    
    def scaffold = true
    
    def create = {
        def projectInstance = new Project()
        projectInstance.properties = params
        return [projectInstance: projectInstance, leaderList: ProjectService.leaderList()]
    }
    
     def show = {
        def projectInstance = Project.get(params.id)
        if (!projectInstance) {
            flash.message = "\${message(code: 'default.not.found.message', args: [message(code: 'Project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
        else {
            [projectInstance: projectInstance, canModify: ProjectService.canModify(projectInstance)]
        }
    }
}
