package com.orangeandbronze.ozmness

class Employee extends User{

	Employee mentor
	Position position
	static hasMany = [mentees: Employee]
	
    static constraints = {
    }
}
