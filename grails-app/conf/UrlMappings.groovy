import com.orangeandbronze.ozmness.*;
class UrlMappings {
	
	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"home", action:"index")
		
		"500"(view:'/error')
		"404"(view:'/error')
	}
}
