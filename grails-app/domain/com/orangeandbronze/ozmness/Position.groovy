package com.orangeandbronze.ozmness

class Position {

	String name
	static hasMany = [minimumRatings: Rating]
	
        static mapping = {table 'positions'}
    static constraints = {
        name(unique:true, nullable:false, blank:false)
    }
	
	String toString(){
		name
	}
}
