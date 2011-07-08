package com.orangeandbronze.ozmness

class Employee extends User{

	Employee mentor
	Position position
	static hasMany = [projects: Project]
	
    static constraints = {
    }
	
	String toString(){
		username
	}
}
