package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_FULLY'])
class EmployeeController {

	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [employeeInstanceList: Employee.list(params), employeeInstanceTotal: Employee.count()]
    }

	@Secured(['ROLE_ADMIN'])
    def create = {
        def employeeInstance = new Employee()
        employeeInstance.properties = params
        return [employeeInstance: employeeInstance]
    }

	@Secured(['ROLE_ADMIN'])
    def save = {
        def employeeInstance = new Employee(params)
		employeeInstance.password = springSecurityService.encodePassword(params.password)
        employeeInstance.enabled = true
		if (employeeInstance.save(flush: true)) {
			def role = Role.get(2)
			def userRoleInstance = new UserRole(role: role, user: employeeInstance)
			userRoleInstance.save(flush: true)
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'employee.label', default: 'Employee'), employeeInstance.id])}"
            redirect(action: "show", id: employeeInstance.id)
        }
        else {
            render(view: "create", model: [employeeInstance: employeeInstance])
        }
    }

    
    def show = {
        def employeeInstance = Employee.get(params.id)
        def currentUser = springSecurityService.currentUser
        def admin = Role.findByAuthority('ROLE_ADMIN')
        def canEdit = (currentUser.getAuthorities().contains(admin)) || (currentUser.id == employeeInstance.id)
        
        if (!employeeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])}"
            redirect(action: "list")
        }
        else {
            [employeeInstance: employeeInstance, canEdit: canEdit]
        }
    }

    def edit = {
        def employeeInstance = Employee.get(params.id)
        if (!employeeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [employeeInstance: employeeInstance]
        }
    }

    def update = {
        def employeeInstance = Employee.get(params.id)
        def password = employeeInstance.password
        if (employeeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (employeeInstance.version > version) {
                    
                    employeeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'employee.label', default: 'Employee')] as Object[], "Another user has updated this Employee while you were editing")
                    render(view: "edit", model: [employeeInstance: employeeInstance])
                    return
                }
            }
            employeeInstance.properties = params
            if(params.password){
			employeeInstance.password = springSecurityService.encodePassword(params.password)
            }else{
                employeeInstance.password = password
            }
            if (!employeeInstance.hasErrors() && employeeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'employee.label', default: 'Employee'), employeeInstance.id])}"
                redirect(action: "show", id: employeeInstance.id)
            }
            else {
                render(view: "edit", model: [employeeInstance: employeeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])}"
            redirect(action: "list")
        }
    }

	@Secured(['ROLE_ADMIN'])
    def delete = {
        def employeeInstance = Employee.get(params.id)
        if (employeeInstance) {
            try {
                employeeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])}"
            redirect(action: "list")
        }
    }
}
