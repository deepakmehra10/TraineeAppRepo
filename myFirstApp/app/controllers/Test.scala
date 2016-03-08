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

case class UserData1(name: String, age: Int,emails:List[String])

class Test extends Controller{

  val userForm = Form(
    mapping(
      "name" -> text,
      "age" -> number.verifying("age less than 50",fields=>fields match{case fields=>validate(fields).isDefined}

      ),"emails"->list(email)
    )(UserData1.apply)(UserData1.unapply)

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
      Ok(views.html.test(userForm))
  }



}
