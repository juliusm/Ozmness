package com.orangeandbronze.ozmness

import grails.test.*

class RatingTests extends GrailsUnitTestCase {
    
    
    protected void setUp() {
        super.setUp()
        mockForConstraintsTests(Rating)
        
        
       
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNullCreatorAndRated() {
        mockDomain(Rating)
        
        def position = new Position(name: 'Software Architect')
        
        def creator = new Employee(username: "emp1", password:"emp1", position:position)
        def rated = new Employee(username: "emp2", password:"emp2", position: position)
        def technology = new Technology(name: "java")
        
        def test = new Rating(creator: null, rated:null, technology:technology, value:2, comment:"good!")
        assertFalse 'validation should fail', test.validate()
        assertEquals 'nullable', test.errors.creator
        assertEquals 'nullable', test.errors.rated
       
        def rating = new Rating(creator: creator, rated:rated, technology:technology, value:2, comment:"very good!")
        assertTrue 'validation should pass', rating.validate()
        
    }
}
