package com.orangeandbronze.ozmness

class Employee extends User {

	Employee mentor
	static hasMany = [mentees: Employee]
	Position position
	
    static constraints = {
    }
}
