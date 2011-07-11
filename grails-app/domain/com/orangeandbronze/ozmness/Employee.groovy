package com.orangeandbronze.ozmness

class Employee extends User{

	Employee mentor
	Position position
	static hasMany = [projects: Project, proteges: Employee, leadProjects: Project]
	
	static mappedBy = [leadProjects: 'lead', projects: 'members']
	static belongsTo = Project
	
    static constraints = {
    }
	
	String toString(){
		username
	}
}
