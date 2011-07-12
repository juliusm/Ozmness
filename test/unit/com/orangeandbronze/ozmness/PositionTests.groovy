package com.orangeandbronze.ozmness

import grails.test.*

class PositionTests extends GrailsUnitTestCase {
    
    def position
    
    protected void setUp() {
        super.setUp()
        mockForConstraintsTests(Position)
        position = new Position(name: 'Associate Software Engineer')
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNameUniqueness() {
        mockDomain(Position)
        def test = new Position(name: 'Associate Software Engineer')
        mockForConstraintsTests(Position, [test])
        assertFalse 'validation should fail', position.validate()
        assertEquals 'unique', position.errors.name
        
        def position = new Position(name: 'Software Architect')
        mockForConstraintsTests(Position, [position])
        assertTrue 'validation should pass', position.validate()
    }
    
    void testBlankPosition(){
        mockDomain(Position)
        def test = new Position(name: "")
        assertFalse 'validation should fail', test.validate()
        assertEquals 'blank', test.errors.name
        
        def position = new Position(name: 'Associate Software Engineer')
        assertTrue 'validation should pass', position.validate()
    }
}
