package com.orangeandbronze.ozmness

class Rating {

	int value
	Technology technology
	Employee rated
	Employee creator
	String comment
	
    static constraints = {
        technology(nullable:false)
       
    }
    
    String toString(){
        'Rated: '+rated+' creator: '+creator+' value: '+value
    }
}
