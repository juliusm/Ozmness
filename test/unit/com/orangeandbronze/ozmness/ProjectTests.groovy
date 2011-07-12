package com.orangeandbronze.ozmness

import grails.test.*

class ProjectTests extends GrailsUnitTestCase {
    
    def project
    def leadEmployee
    def memberEmployee
    def technology
    
    protected void setUp() {
        super.setUp()
        mockForConstraintsTests(Project)
        Position position = new Position(name: "assoc dev")
        leadEmployee = new Employee(username: "emp1", password:"emp1", position:position)
        memberEmployee = new Employee(username: "emp2", password:"emp2", position: position)
        project = new Project(name: "projektong google +", lead: leadEmployee)
        technology = new Technology(name: "java")
        project.members = []
        project.technologies =[]
        project.technologies.add(technology)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAddEmployee() {
        mockDomain(Project)
        assertTrue project.members.size() == 0
        project.members.add(memberEmployee)
        assertTrue project.members.size() == 1
        assertTrue project.members.contains(memberEmployee)
    }
    
    void testProjectWoLead(){
        mockDomain(Project)
        def test = new Project(name:'project', lead: null)
        test.technologies = []
        test.technologies.add(technology)
        assertFalse 'validation should fail', test.validate()
        assertEquals 'nullable', test.errors.lead
        
        def project = new Project(name: 'project2', lead:leadEmployee)
        assertTrue 'validation should pass', project.validate()
    }
    
    void testNameUniqueness() {
        mockDomain(Project)
        def test = new Project(name: "projektong google +", lead:leadEmployee)
        test.technologies = []
        test.technologies.add(technology)
        mockForConstraintsTests(Project, [test])
        assertFalse 'validation should fail', project.validate()
        assertEquals 'unique', project.errors.name
        
        def project = new Project(name: 'proyekto4', lead:leadEmployee)
        project.technologies =[]
        project.technologies.add(technology)
        mockForConstraintsTests(Project, [project])
        assertTrue 'validation should pass', project.validate()
    }
    
    void testBlankProjectName(){
        def test = new Project(name: '', lead:leadEmployee)
        test.technologies =[]
        test.technologies.add(technology)
        assertFalse 'validation should fail', test.validate()
        assertEquals 'blank', test.errors.name
    }
    
    void testWithoutTechnology(){
        def test = new Project(name: 'proyekto2', lead:leadEmployee)
        test.technologies =[]
        assertFalse 'validation should fail', test.validate()
        assertEquals 'minSize', test.errors.technologies
      
    }
}
