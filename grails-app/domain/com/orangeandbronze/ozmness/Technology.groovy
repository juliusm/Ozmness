package com.orangeandbronze.ozmness

class Technology {

	String name
	Technology parent
	
    static constraints = {
    	parent(nullable:true)
        name(nullable: false, blank:false, unique:true)
		}
	
	String toString(){
		name
	}
}
