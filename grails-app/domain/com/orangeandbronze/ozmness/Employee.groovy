package com.orangeandbronze.ozmness

class Employee {

	Employee mentor
	static hasMany = [mentees: Employee]
	Position position
	
    static constraints = {
    }
}
