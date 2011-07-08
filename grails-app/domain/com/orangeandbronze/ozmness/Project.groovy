package com.orangeandbronze.ozmness

class Project {
	
	Employee lead
	String name
	static hasMany = [technologies: Technology]
	
    static constraints = {
    }
	
	String toString(){
		name
	}
}
