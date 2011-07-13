package com.orangeandbronze.ozmness

import grails.test.*

class TechnologyControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testList() {
        mockDomain(Technology, [new Technology(name:"spring"),
                                new Technology(name:"hibernate")])
        def model = controller.list()
        assertEquals 2, model.technologyInstanceList.size()
    }
}
