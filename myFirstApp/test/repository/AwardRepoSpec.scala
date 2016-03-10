package repository

import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by akshay on 10/3/16.
  */
class AwardRepoSpec extends Specification{


  "Award repository" should {
    def awardRepo(implicit app: Application) = Application.instanceCache[AwardRepo].apply(app)

    "Get all award record" in new WithApplication {
      val result = Await.result(awardRepo.getAllAwards(), 5 seconds)
      assert(result.length === 2)
    }

    "Add record" in new WithApplication {
      val result = Await.result(awardRepo.addAwards(Awards(3,"sangeeta","first division",2016)), 5 seconds)
      assert(result === 1)
    }
  }
}
