package controllers

import javax.inject.Inject

import connection.DbComponent
import models.User
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.Action
import play.api.mvc._
import repository.{UserRepo, UserRepoApi}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future._
import play.api.i18n.Messages.Implicits._
import play.api.Play.current

import scala.concurrent.{Await, Future}

/**
  * Created by knoldus on 26/2/16.
  */

case class UsersData(username: String,email:String,password:String,repassword:String)
case class LoginData(username:String,password:String)

class UsersController @Inject()(user:UserRepoApi) extends Controller {

  val signupForm = Form(
    mapping(
      "username" -> text,
      "email" -> text,
      "password"->text,
      "repassword"->text
    )(UsersData.apply)(UsersData.unapply)

  )

  val loginForm = Form(
    mapping(
      "username" -> nonEmptyText,
      "password"->nonEmptyText

    )(LoginData.apply)(LoginData.unapply).verifying("Invalid User", res=> res match{
      case LoginData(u,p) => checkValidation(u,p)
    })
  )

  def checkValidation(username:String,password:String): Boolean={


    val result:Future[List[User]]=user.validateUser(username,password)
    //println(result);
    val finalResult=result.map(x => if(x.length==1)
    true
    else false)

    Await.result(finalResult,3 seconds)
  }


  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.loginusers(formWithErrors)),
      loginData => Ok("success")

    )
  }


  def renderSignUp() = Action {
    implicit request=>
      Ok(views.html.signup(signupForm))
  }

  def renderLogin() = Action {
    implicit request=>
      Ok(views.html.loginusers(loginForm))
  }

  def renderHomepage= Action {
    implicit request=>
      Ok(views.html.homepage())
  }

  def renderadminHomepage= Action {
    implicit request=>
      Ok(views.html.adminhomepage())
  }

}
