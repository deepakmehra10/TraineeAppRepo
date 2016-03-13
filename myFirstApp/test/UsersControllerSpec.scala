import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
  * Created by knodus on 9/3/16.
  */
class UsersControllerSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get
      status(home) must equalTo(303)
    }

    "render the login page" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/login")).get
      status(home) must equalTo(OK)
    }

    "render the authenticate login with Bad Data" in new WithApplication{
      val home = route(FakeRequest(POST, "/users/authenticate").withFormUrlEncodedBody("username"->"sangeeta","password"->"12")).get
      status(home) must equalTo(303)
    }

    "render the authenticate login with Correct Data" in new WithApplication{
      val home = route(FakeRequest(POST, "/users/authenticate").withFormUrlEncodedBody("username"->"sangeeta","password"->"123")).get
      status(home) must equalTo(303)
    }

    "render the authenticate login with Unauthorised Access" in new WithApplication{
      val home = route(FakeRequest(POST, "/users/authenticate").withFormUrlEncodedBody("username"->"abc","password"->"xyz")).get
      status(home) must equalTo(303)
    }

    "render Home page with admin: Unauthorised Access" in new WithApplication{
      val home = route(FakeRequest(GET, "/homepage").withFormUrlEncodedBody("username"->"admin","password"->"admin")).get
      status(home) must equalTo(401)
    }

    "render Home page with admin: authorised Access" in new WithApplication{
      val home = route(FakeRequest(GET, "/homepage").withSession("connected"->"admin")).get
      status(home) must equalTo(OK)
    }

    "render Home page with user: authorised Access" in new WithApplication{
      val home = route(FakeRequest(GET, "/homepage").withSession("connected"->"sangeeta")).get
      status(home) must equalTo(OK)
    }

    "render Home page with trainee account: Unauthorised Access" in new WithApplication{
      val home = route(FakeRequest(GET, "/homepage").withFormUrlEncodedBody("username"->"sangeeta","password"->"123")).get
      status(home) must equalTo(401)
    }



    "logout the user" in new WithApplication{
      val home = route(FakeRequest(GET, "/user/login")).get
      status(home) must equalTo(303)
    }

    "get awards of user:Admin" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/getawards")).get
      status(home) must equalTo(OK)
    }

    "get languages of user:Admin" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/getlanguage")).get
      status(home) must equalTo(OK)
    }

    "get assignments of user:Admin" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/getassignment")).get
      status(home) must equalTo(OK)
    }

    "get programming language of user: Admin" in new WithApplication{

      val home = route(FakeRequest(GET, "/users/getprogramming")).get
      status(home) must equalTo(OK)
    }

    "get awards of user" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/getuserawards").withSession("connected"->"sangeeta")).get
      status(home) must equalTo(OK)
    }

    "get languages of user" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/getuserlanguage").withSession("connected"->"sangeeta")).get
      status(home) must equalTo(OK)
    }

    "get assignments of user" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/getuserassignment").withSession("connected"->"sangeeta")).get
      status(home) must equalTo(OK)
    }

    "get programming language of user" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/getuserprogramming").withSession("connected"->"sangeeta")).get
      status(home) must equalTo(OK)
    }

    "get all interns" in new WithApplication{
      val home = route(FakeRequest(GET, "/users/getintern")).get
      status(home) must equalTo(OK)
    }


    "Add interns: Admin" in new WithApplication{
      val home = route(FakeRequest(POST, "/admin/addintern").withFormUrlEncodedBody("name"->"deepak","email"->"deep@gmail.com","mobile"->"3546354","award"->"C#")).get
      status(home) must equalTo(OK)
    }

    "Add assignment : Admin" in new WithApplication{
      val home = route(FakeRequest(POST, "/admin/addassignment").withFormUrlEncodedBody( "sno" -> "999","studname"->"antra","name" ->"Assignment-02","date"->"03/10/2016","marks"->"7","remarks"->" Validation not complete")).get
      status(home) must equalTo(OK)

    }
  }
}

