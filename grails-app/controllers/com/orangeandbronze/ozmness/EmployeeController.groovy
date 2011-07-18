package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_FULLY'])
class EmployeeController {
        
    def scaffold = true
        
    @Secured(['ROLE_ADMIN'])
    def save = {
        def employeeInstance = new Employee(params)

        if (employeeInstance.save(flush: true)) {
            def role = Role.findByAuthority('ROLE_DEV')
            UserRole.create(employeeInstance, role, true)
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'employee.label', default: 'Employee'), employeeInstance.id])}"
            redirect(action: "show", id: employeeInstance.id)
        }
        else {
            render(view: "create", model: [employeeInstance: employeeInstance])
        }
    }


}
