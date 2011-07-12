package com.orangeandbronze.ozmness

import grails.test.*

class RatingTests extends GrailsUnitTestCase {
    
    def creator
    def rated
    def technology
    
    protected void setUp() {
        super.setUp()
        mockForConstraintsTests(Rating)
        Position position = new Position(name: 'Software Architect')
        creator = new Employee(username: "emp1", password:"emp1", position:position)
        rated = new Employee(username: "emp2", password:"emp2", position: position)
        technology = new Technology(name: "java")
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNullCreatorAndRated() {
        mockDomain(Rating)
        def test = new Rating(creator: null, rated:null, technology:technology, value:2)
        assertFalse 'validation should fail', test.validate()
        assertEquals 'nullable', test.errors.creator
        assertEquals 'nullable', test.errors.rated
       
    }
}
