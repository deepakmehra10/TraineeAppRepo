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

case class UserData(name: String, age: Int,emails:List[String])

class Authentication extends Controller{

  val userForm = Form(
    mapping(
      "name" -> text,
      "age" -> number.verifying("age less than 50",fields=>fields match{case fields=>validate(fields).isDefined}

      ),"emails"->list(email)
    )(UserData.apply)(UserData.unapply)

  )

  def validate(age: Int) = {
    age match {

      case age if age<=50 =>
        Some(age)
      case _=>
        None
          }
  }

  def login() = Action {
    implicit request=>
    Ok(views.html.login(userForm))
  }

  def authenticated()=Action{
    implicit request=>
      userForm.bindFromRequest.fold(
        formWithErrors => {
          // binding failure, you retrieve the form containing errors:
 //      Redirect(routes.Authentication.login).flashing("error"->"incorrect username or age")
       BadRequest(views.html.login(formWithErrors)).flashing("error"->"incorrect username or age")

        },
        userData => {
          val newUser = (userData.name, userData.age,userData.emails)
       // Ok("Welcome "+newUser).flashing("success"->"Successfully logged in")
Redirect(routes.Authentication.authenticated).flashing("success"->"Successfully logged in")
        //  Ok.sendFile(new java.io.File("/home/knoldus/Desktop/maxresdefault.jpg"),inline = true)

        }
      )

  }



}
