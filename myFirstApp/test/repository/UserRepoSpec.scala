package repository

import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.duration._
import scala.concurrent.Await


/**
  * Created by akshay on 9/3/16.
  */
class UserRepoSpec extends Specification {

  import models._

  "User repository" should {
    def userRepo(implicit app: Application) = Application.instanceCache[UserRepo].apply(app)

    "check user validity" in new WithApplication {
      val result = Await.result(userRepo.validateUser("test", "123"), 5 seconds)
      assert(result.length === 1)
    }

    "insert test" in new WithApplication{
      val result = Await.result(userRepo.insert(User("akshay","123",true,2)), 5 seconds)
      assert(result === 1)
    }

  }

}