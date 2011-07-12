import com.orangeandbronze.ozmness.*

class BootStrap {

   def springSecurityService

   def init = { servletContext ->

      def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
      def userRole = new Role(authority: 'ROLE_DEV').save(flush: true)

      String password = springSecurityService.encodePassword('password')
      def testUser = new User(username: 'me', enabled: true, password: password)
      testUser.save(flush: true)

      UserRole.create testUser, adminRole, true
   }
}