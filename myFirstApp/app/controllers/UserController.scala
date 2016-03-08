package controllers

import com.google.inject.Inject
import model.{UserServiceApi, Users}
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.{Await, Future}

/**
  * Created by knoldus on 27/2/16.
  */
class UserController @Inject()(user:UserServiceApi) extends Controller {

def index=Action.async{

  val futureValue=user.getUsers()
  val futureResult=futureValue.map(value=>Ok("My values"+value))
 futureResult
}

}
