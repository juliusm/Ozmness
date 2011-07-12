package com.orangeandbronze.ozmness

class Employee extends User{

	Employee mentor
	Position position
	static hasMany = [projects: Project, proteges: Employee, leadProjects: Project]
	
	static mappedBy = [leadProjects: 'lead', projects: 'members']
	static belongsTo = Project
	
    static constraints = {
        position(nullable: false)
        username(unique: true, nullable:false, blank:false)
        password(nullable:false)
    }
	
	String toString(){
		username
	}
}
