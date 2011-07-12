package com.orangeandbronze.ozmness

import grails.test.*

class EmployeeTests extends GrailsUnitTestCase {
    
    def employee
    def position
    
    protected void setUp() {
        super.setUp()
        mockForConstraintsTests(Employee)
        position = new Position(name: 'Associate Software Engineer')
        employee = new Employee(username: 'testusername', password: 'testpassword', position: position, enabled:true)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNullPosition() {
        mockDomain(Employee)
        Employee employee1 = new Employee(username: 'testusername3', password: 'testpassword', position: null)
        assertFalse 'validation should have failed', employee1.validate()
        assertEquals "nullable", employee1.errors.position
        Employee employee2 = new Employee(username: 'testusername1', password: 'testpassword', position: position)
        assertTrue 'validation should pass', employee2.validate()
    }
    
    void testUsernameUniqueness(){
        
        def employee2 = new Employee(username: 'testusername', password: 'testpassword', position: position)
        mockForConstraintsTests(Employee, [employee2])        
        assertFalse 'validation should fail', employee.validate()        
        assertEquals 'unique', employee.errors.username        
        def employee3 = new Employee(username: 'username', password: 'testpassword', position: position)        
        mockForConstraintsTests(Employee, [employee3])        
        assertTrue 'validation should pass', employee.validate()
    }
    
    void testBlankUsernameAndPassword(){
        mockDomain(Employee)
        
        def test = new Employee(username:'', password:'', position: position)
        assertFalse 'validation should fail', test.validate()
        
        assertEquals 'blank', test.errors.username
        assertEquals 'blank', test.errors.password
        
        def employee =  new Employee(username: 'testusername', password: 'testpassword', position: position)
        assertTrue 'validation should pass', employee.validate()
    }
}
