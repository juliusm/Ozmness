package com.orangeandbronze.ozmness

class Position {

	String name
	static hasMany = [minimumRatings: Rating]
	
    static constraints = {
    }
	
	String toString(){
		name
	}
}
