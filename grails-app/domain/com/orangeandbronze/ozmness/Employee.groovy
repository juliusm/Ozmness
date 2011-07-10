package com.orangeandbronze.ozmness

class Employee extends User{

	Employee mentor
	Position position
	static hasMany = [projects: Project_Employee, proteges: Employee]
	
    static constraints = {
    }
	
	String toString(){
		username
	}
}
