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
      assert(result === List(Assignment(1,"akshay","scala","1st jan",6,"average"),Assignment(2,"deepak","scala","3rd jan",6,"average")))
    }

    "Add assignment test" in new WithApplication {
      val result = Await.result(assignmentRepo.addAssignment(Assignment(3,"Sangeeta","Scala","1st jan",6,"average")), 5 seconds)
      assert(result === 1)
    }

    "Get assignment record test" in new WithApplication {
      val result = Await.result(assignmentRepo.getUserAssignment("akshay"), 5 seconds)
      assert(result.length === 1)
      assert(result === List(Assignment(1,"akshay","scala","1st jan",6,"average")))
    }
  }




}