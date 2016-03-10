package repository

import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by akshay on 10/3/16.
  */
class ProgrammingLanguageRepoSpec  extends Specification{

  "Programming Language repository" should {
    def progLanguageRepo(implicit app: Application) = Application.instanceCache[ProgrammingLanguageRepo].apply(app)

    "Get all programming language records Test" in new WithApplication {
      val result = Await.result(progLanguageRepo.getAllProgLanguage, 5 seconds)
      assert(result.length === 2)
      assert(result === List(ProgrammingLanguage(1,"akshay","scala","read/write"),ProgrammingLanguage(2,"deepak","scala","read/write")))

    }

    "Get user programming language Test" in new WithApplication {
      val result = Await.result(progLanguageRepo.getUserProgLanguage("deepak"), 5 seconds)
      assert(result.length === 1)
      assert(result === List(ProgrammingLanguage(2,"deepak","scala","read/write")))

    }


    "Add programming language Test" in new WithApplication {
      val result = Await.result(progLanguageRepo.addProgLanguage(ProgrammingLanguage(3,"Sangeeta","Scala","r/w")), 5 seconds)
      assert(result === 1)
    }
  }
}