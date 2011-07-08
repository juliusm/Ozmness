package com.orangeandbronze.ozmness

class Technology {

	String name
	Technology parent
	
    static constraints = {
    	parent(nullable:true)
		}
	
	String toString(){
		name
	}
}
