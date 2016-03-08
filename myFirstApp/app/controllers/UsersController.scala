package controllers

import javax.inject.Inject

import play.api.data._
import play.api.data.Forms._
import play.api.mvc.Action
import play.api.mvc._
import scala.concurrent.Future._
import play.api.i18n.Messages.Implicits._
import play.api.Play.current

import scala.concurrent.Future

/**
  * Created by knoldus on 26/2/16.
  */

case class UsersData(username: String,email:String,password:String,repassword:String)
case class LoginData(username:String,password:String)
class UsersController extends Controller{

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
      "username" -> text,
      "password"->text

    )(LoginData.apply)(LoginData.unapply)

  )



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
