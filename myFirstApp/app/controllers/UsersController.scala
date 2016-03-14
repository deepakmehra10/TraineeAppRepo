package controllers

import javax.inject.Inject

import models.User
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.Action
import play.api.mvc._
import repository._
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

class UsersController @Inject()(user:UserRepo,award:AwardRepo,language:LanguageRepo,assignment:AssignmentRepo,
                                intern:InternRepo,programming:ProgrammingLanguageRepo) extends Controller {


  val loginForm = Form(
    mapping(
      "username" -> nonEmptyText,
      "password"->nonEmptyText

    )(LoginData.apply)(LoginData.unapply)
  )

  val awardForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "details"->nonEmptyText,
      "year"->number,
      "sno"->number
    )(repository.Awards.apply)(repository.Awards.unapply)
  )


  val internForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "email"->nonEmptyText,
      "mobile"->number,
      "award"->nonEmptyText

    )(repository.Interns.apply)(repository.Interns.unapply)
  )

  val assignmentForm = Form(
    mapping(
      "studname"-> nonEmptyText,
      "name" -> nonEmptyText,
      "date"->nonEmptyText,
      "marks"->number,
      "remarks"->nonEmptyText,
      "sno" -> number
    )(repository.Assignment.apply)(repository.Assignment.unapply)
  )


  def checkValidation(username:String,password:String): Future[Boolean]={
    val result:Future[List[User]]=user.validateUser(username,password)
    result.map{x => if(x.length==1) true  else false}

  }

  def authenticate = Action.async { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => Future{Redirect(routes.UsersController.renderLogin()).
        flashing("error"->" Enter data in correct format")},
      loginData => {
        val result=checkValidation(loginData.username,loginData.password)
        result.map{ response => if( response == true) {
          Redirect(routes.UsersController.renderHomepage).withSession("connected" -> loginData.username)
        }else {
          Redirect(routes.UsersController.renderLogin()).flashing("error" -> " Enter valid Details")
        }
        }
      }
    )
  }

  def renderHomepage=Action { implicit request=>
    //Ok("success")
    request.session.get("connected").map { user =>
      if(user=="admin") {
        Ok(views.html.adminhomepage(internForm,assignmentForm))
      }else {
        Ok(views.html.homepage(awardForm))
      }}.getOrElse{
       Unauthorized("Oops, you are not connected")
       }

    }



  def renderLogin() = Action {
    implicit request=>
      Ok(views.html.loginusers(loginForm))
  }


/*
  def renderadminHomepage= Action {
    implicit request=>
      Ok(views.html.adminhomepage())
  }
*/
  def renderuserHomepage= Action {
    implicit request=>
      Ok(views.html.homepage(awardForm))
  }


  def logout=Action{
    implicit request=>
    Redirect(routes.UsersController.renderLogin()).withNewSession
  }

 def getAwards=Action.async{ implicit request =>
    award.getAllAwards.map{ data =>
      Ok(views.html.adminDashboard.awardTable(data))
    }
  }

 def getLanguages=Action.async{ implicit request =>
    language.getAllLanguage.map{ data =>
      Ok(views.html.adminDashboard.languageTable(data))
    }
  }

  def getAssignment=Action.async{ implicit request =>
    assignment.getAllAssignment.map{ data =>
      Ok(views.html.adminDashboard.assignmentTable(data))
    }

  }

  def getProgramming=Action.async{ implicit request =>
    programming.getAllProgLanguage.map{ data =>
      Ok(views.html.adminDashboard.progLanguageTable(data))
    }
  }

  def getUserAwards=Action.async{ implicit request =>
    val user= request.session.get("connected")
    val username= user.get
       award.getUserAwards(username).map { data =>
         Ok(views.html.adminDashboard.awardTable(data))
       }
  }

  def getUserAssignment=Action.async{ implicit request =>
    val user= request.session.get("connected")
    val username= user.get
    assignment.getUserAssignment(username).map { data =>
      Ok(views.html.adminDashboard.assignmentTable(data))
    }
  }

  def getUserLanguage=Action.async{ implicit request =>
    val user= request.session.get("connected")
    val username= user.get
    language.getUserLanguage(username).map { data =>
      Ok(views.html.adminDashboard.languageTable(data))
    }
  }

  def getUserProgramming=Action.async{ implicit request =>
    val user= request.session.get("connected")
    val username= user.get
    programming.getUserProgLanguage(username).map { data =>
      Ok(views.html.adminDashboard.progLanguageTable(data))
    }

  }

  def getInterns=Action.async{ implicit request =>
    intern.getAllInterns.map{ data =>
      Ok(views.html.adminDashboard.internTable(data))
    }
  }

  def addInterns=Action.async { implicit request =>

      val internRecord = internForm.bindFromRequest.get
      intern.addInterns(internRecord)
      intern.getAllInterns.map { data =>
        Ok(views.html.adminhomepage(internForm,assignmentForm))
      }
    }

  def addAssignments=Action.async { implicit request =>

    val assignmentRecord = assignmentForm.bindFromRequest.get
    assignment.addAssignment(assignmentRecord)
    assignment.getAllAssignment.map { data =>
      Ok(views.html.adminhomepage(internForm,assignmentForm))
    }

  }

  def addAwards=Action.async { implicit request =>

    val awardRecord = awardForm.bindFromRequest.get
    award.addAwards(awardRecord)
    award.getAllAwards().map { data =>
      Ok(views.html.homepage(awardForm))
    }
  }


}
