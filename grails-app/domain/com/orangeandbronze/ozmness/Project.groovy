package com.orangeandbronze.ozmness

class Project {
	
	Employee lead
	String name
	static hasMany = [technologies: Technology, members: Employee]
	
	
    static constraints = {
        lead(nullable:false)
        name(nullable:false, blank:false, unique:true)
        
    }
	
	String toString(){
		name
	}
}
