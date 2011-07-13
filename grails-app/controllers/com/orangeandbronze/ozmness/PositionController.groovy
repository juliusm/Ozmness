package com.orangeandbronze.ozmness

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class PositionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [positionInstanceList: Position.list(params), positionInstanceTotal: Position.count()]
    }

    def create = {
        def positionInstance = new Position()
        positionInstance.properties = params
        
        def technologies = Technology.list()
        def ratings = []
        for(technology in technologies){
                def ratingInstance = new Rating()
                ratingInstance.properties = params
                ratingInstance.technology = technology
                ratings.add(ratingInstance)
                }
        
        return [positionInstance: positionInstance, ratingsList: ratings]
    }

    def save = {
        def positionInstance = new Position(name:params.name)
        if (positionInstance.save(flush: true)) {
            
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'position.label', default: 'Position'), positionInstance.id])}"
            redirect(action: "show", id: positionInstance.id)
        }
        else {
            render(view: "create", model: [positionInstance: positionInstance])
        }
    }

    def show = {
        def positionInstance = Position.get(params.id)
        if (!positionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
            redirect(action: "list")
        }
        else {
            [positionInstance: positionInstance]
        }
    }

    def edit = {
        def positionInstance = Position.get(params.id)
        if (!positionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [positionInstance: positionInstance]
        }
    }

    def update = {
        def positionInstance = Position.get(params.id)
        if (positionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (positionInstance.version > version) {
                    
                    positionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'position.label', default: 'Position')] as Object[], "Another user has updated this Position while you were editing")
                    render(view: "edit", model: [positionInstance: positionInstance])
                    return
                }
            }
            positionInstance.properties = params
            if (!positionInstance.hasErrors() && positionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'position.label', default: 'Position'), positionInstance.id])}"
                redirect(action: "show", id: positionInstance.id)
            }
            else {
                render(view: "edit", model: [positionInstance: positionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def positionInstance = Position.get(params.id)
        if (positionInstance) {
            try {
                positionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
            redirect(action: "list")
        }
    }
}
