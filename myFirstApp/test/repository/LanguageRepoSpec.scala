package repository


import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by akshay on 10/3/16.
  */
class LanguageRepoSpec extends Specification{

  "Language repository" should {
    def languageRepo(implicit app: Application) = Application.instanceCache[LanguageRepo].apply(app)

    "Get all language records Test" in new WithApplication {
      val result = Await.result(languageRepo.getAllLanguage, 5 seconds)
      assert(result.length === 2)
      assert(result === List(Language(1,"akshay","hindi","read/write"),Language(2,"deepak","english","read/write")))
    }

    "Get user language Test" in new WithApplication {
      val result = Await.result(languageRepo.getUserLanguage("deepak"), 5 seconds)
      assert(result.length === 1)
      assert(result === List(Language(2,"deepak","english","read/write")))
    }


    "Add language Test" in new WithApplication {
      val result = Await.result(languageRepo.addLanguage(Language(3,"Sangeeta","English","r/w")), 5 seconds)
      assert(result === 1)
    }
  }
}