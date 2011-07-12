package com.orangeandbronze.ozmness

import grails.test.*

class TechnologyTests extends GrailsUnitTestCase {
    
    def technology
    
    protected void setUp() {
        super.setUp()
        mockForConstraintsTests(Technology)
        technology = new Technology(name: 'spring')
    }

    protected void tearDown() {
        super.tearDown()
    }

     void testNameUniqueness() {
        mockDomain(Technology)
        def test = new Technology(name: 'spring')
        mockForConstraintsTests(Technology, [test])
        assertFalse 'validation should fail', technology.validate()
        assertEquals 'unique', technology.errors.name
        
        def technology = new Technology(name: 'java')
        mockForConstraintsTests(Technology, [technology])
        assertTrue 'validation should pass', technology.validate()
    }
    
    void testBlankTechnologyName(){
        mockDomain(Technology)
        def test = new Technology(name: "")
        assertFalse 'validation should fail', test.validate()
        assertEquals 'blank', test.errors.name
        
        def technology = new Technology(name: 'hibernate')
        assertTrue 'validation should pass', technology.validate()
    }
}
