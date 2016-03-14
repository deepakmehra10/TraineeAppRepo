package repository

import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by akshay on 10/3/16.
  */
class AssignmentRepoSpec extends Specification{

  "Assignment repository" should {
    def assignmentRepo(implicit app: Application) = Application.instanceCache[AssignmentRepo].apply(app)

    "Get all assignment records test" in new WithApplication {
      val result = Await.result(assignmentRepo.getAllAssignment, 5 seconds)
      assert(result.length === 2)
      assert(result === List(Assignment("akshay","scala","1st jan",6,"average",1),Assignment("deepak","scala","3rd jan",6,"average",2)))
    }

    "Add assignment test" in new WithApplication {
      val result = Await.result(assignmentRepo.addAssignment(Assignment("Sangeeta","Scala","1st jan",6,"average",3)), 5 seconds)
      assert(result === 1)
    }

    "Get assignment record test" in new WithApplication {
      val result = Await.result(assignmentRepo.getUserAssignment("akshay"), 5 seconds)
      assert(result.length === 1)
      assert(result === List(Assignment("akshay","scala","1st jan",6,"average",1)))
    }
  }
}