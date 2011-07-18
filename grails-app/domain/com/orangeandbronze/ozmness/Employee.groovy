package com.orangeandbronze.ozmness

class Employee extends User{

        String name
	Employee mentor
	Position position
	static hasMany = [projects: Project, proteges: Employee, leadProjects: Project]
	
	static mappedBy = [leadProjects: 'lead', projects: 'members']
	static belongsTo = Project
	
   
    static constraints = {
        position(nullable: false)
        name(nullable: false, blank:false)
    }
        
	String toString(){
		name
	}
}
