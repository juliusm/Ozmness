package com.orangeandbronze.ozmness

class Project_EmployeeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [project_EmployeeInstanceList: Project_Employee.list(params), project_EmployeeInstanceTotal: Project_Employee.count()]
    }

    def create = {
        def project_EmployeeInstance = new Project_Employee()
        project_EmployeeInstance.properties = params
        return [project_EmployeeInstance: project_EmployeeInstance]
    }

    def save = {
        def project_EmployeeInstance = new Project_Employee(params)
        if (project_EmployeeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'project_Employee.label', default: 'Project_Employee'), project_EmployeeInstance.id])}"
            redirect(action: "show", id: project_EmployeeInstance.id)
        }
        else {
            render(view: "create", model: [project_EmployeeInstance: project_EmployeeInstance])
        }
    }

    def show = {
        def project_EmployeeInstance = Project_Employee.get(params.id)
        if (!project_EmployeeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project_Employee.label', default: 'Project_Employee'), params.id])}"
            redirect(action: "list")
        }
        else {
            [project_EmployeeInstance: project_EmployeeInstance]
        }
    }

    def edit = {
        def project_EmployeeInstance = Project_Employee.get(params.id)
        if (!project_EmployeeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project_Employee.label', default: 'Project_Employee'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [project_EmployeeInstance: project_EmployeeInstance]
        }
    }

    def update = {
        def project_EmployeeInstance = Project_Employee.get(params.id)
        if (project_EmployeeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (project_EmployeeInstance.version > version) {
                    
                    project_EmployeeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'project_Employee.label', default: 'Project_Employee')] as Object[], "Another user has updated this Project_Employee while you were editing")
                    render(view: "edit", model: [project_EmployeeInstance: project_EmployeeInstance])
                    return
                }
            }
            project_EmployeeInstance.properties = params
            if (!project_EmployeeInstance.hasErrors() && project_EmployeeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'project_Employee.label', default: 'Project_Employee'), project_EmployeeInstance.id])}"
                redirect(action: "show", id: project_EmployeeInstance.id)
            }
            else {
                render(view: "edit", model: [project_EmployeeInstance: project_EmployeeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project_Employee.label', default: 'Project_Employee'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def project_EmployeeInstance = Project_Employee.get(params.id)
        if (project_EmployeeInstance) {
            try {
                project_EmployeeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'project_Employee.label', default: 'Project_Employee'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'project_Employee.label', default: 'Project_Employee'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project_Employee.label', default: 'Project_Employee'), params.id])}"
            redirect(action: "list")
        }
    }
}
