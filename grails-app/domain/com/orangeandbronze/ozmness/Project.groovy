package com.orangeandbronze.ozmness

class Project {
	
	Employee lead
	static hasManyTechnologies = [technologies: Technology]
	static hasManyMembers = [members: Employee]
	
    static constraints = {
    }
}
