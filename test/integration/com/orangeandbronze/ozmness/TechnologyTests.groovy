package com.orangeandbronze.ozmness

import grails.test.*

class TechnologyTests extends GroovyTestCase {
    
    protected void setUp() {
        super.setUp()
       new Technology(name:"java").save()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        
        assertEquals 1, Technology.list().size()
        
        def controller = new TechnologyController()
        controller.params.name = "spring"
        
        controller.save()
        def tech = controller.list().technologyInstanceList
        assertEquals 2, tech.size()
        assertEquals 1, tech[0].id
        assertEquals Technology.findByName("spring"), tech[1]
     
    }
    
    void testUpdate(){
        def controller = new TechnologyController()
        
        int id = Technology.findByName("java").id
       
        controller.params.id = id
        def tech1 = controller.show().technologyInstance
        assertEquals id, tech1.id
        
        controller.params.name = "hibernate"
        controller.params.version = 0
        controller.update()
        
        def tech2 = Technology.get(id)
        
        assertEquals "hibernate", tech2.name
    }
    
}
