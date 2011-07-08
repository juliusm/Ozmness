package com.orangeandbronze.ozmness

class Position {

	String name
	static hasMany = [ratings: Rating]
	
    static constraints = {
    }
	
	String toString(){
		name
	}
}
