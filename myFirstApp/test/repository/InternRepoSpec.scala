package repository

import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.duration._
import scala.concurrent.Await


/**
  * Created by akshay on 11/3/16.
  */
class InternRepoSpec extends Specification{


  "Intern repository" should {
    def internRepo(implicit app: Application) = Application.instanceCache[InternRepo].apply(app)

    "Get all interns records test" in new WithApplication {
      val result = Await.result(internRepo.getAllInterns(), 5 seconds)
      assert(result.length === 2)
      assert(result === List(Interns("sangeeta", "sang@gmail.com", 4342534, "Java Certified"), Interns("admin","admin@gmail.com",2432466,"C# Certified")))
    }

    "Add intern test" in new WithApplication {
      val result = Await.result(internRepo.addInterns(Interns("akshay", "aksh@gmail.com", 434234, "C Certified")), 5 seconds)
      assert(result === 1)
    }
  }
}