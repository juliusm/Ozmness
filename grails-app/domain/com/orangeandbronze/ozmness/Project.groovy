package com.orangeandbronze.ozmness

class Project {
	
	String name
	static hasMany = [technologies: Technology, employees: Project_Employee]
	
	
    static constraints = {
    }
	
	String toString(){
		name
	}
}
