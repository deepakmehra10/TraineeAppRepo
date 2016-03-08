/**
  * Created by knoldus on 27/2/16.
  */
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class AuthenticationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/login")) must beSome.which(status(_) == 200)
    }



    "render the index page" in new WithApplication {
      val home = route(FakeRequest(GET, "/login")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("emails")
    }

    "unauthorized access" in new WithApplication {
      val home = route(FakeRequest(POST, "/auth").withFormUrlEncodedBody("name"->"deepak","age"->"49","emails"->"deep@gmail.com,d@gmail.com")).get

      status(home) must equalTo(303)
      contentType(home) must beSome.which(_ == "text/plain")
      contentAsString(home) must contain("Welcome")
    }


  }
}

