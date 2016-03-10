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

  }
}

