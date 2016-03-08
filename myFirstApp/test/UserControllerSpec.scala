
import controllers.UserController
import model.UserServiceApi
import org.mockito.Mock
import org.specs2.mock.Mockito
import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test._
import org.mockito.Mockito._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by knoldus on 27/2/16.
  */
class UserControllerSpec extends Specification with Mockito {

  val service = mock[UserServiceApi]

  val controller =new UserController(service)
  "userListing" in new WithApplication {

    when(service.getUsers()).thenReturn(Future(Nil))
    val home = call(controller.index,(FakeRequest(GET, "/getUsers")))

    status(home) must equalTo(OK)
//    contentType(home) must beSome.which(_ == "text/html")
  //  contentAsString(home) must contain("emails")
  }



}
