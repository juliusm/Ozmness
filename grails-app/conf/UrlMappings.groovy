class UrlMappings {
	
	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"Home", action:"index")
		
		"500"(view:'/error')
		"404"(view:'/error')
	}
}
