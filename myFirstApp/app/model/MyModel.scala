package model

import com.google.inject.ImplementedBy
import org.h2.engine.User
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by knoldus on 27/2/16.
  */
case class Users(name:String,age:Int,emails:String)

@ImplementedBy(classOf[UserService])
trait UserServiceApi{

  def getUsers(): Future[List[Users]]

  }
  class UserService extends UserServiceApi
  {
    def getUsers()= {
      println("heelo")
      Future (

        List(Users("deepak", 45, "dee@gmail.com"), Users("akhsya", 89, "akshay@gmail.com"))

      )

    }

}