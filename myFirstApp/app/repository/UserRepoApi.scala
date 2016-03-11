package repository


import javax.inject.{Inject,Singleton}

import slick.driver.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext.Implicits.global

import models.User

import scala.concurrent.Future


/**
  * Created by knodus on 8/3/16.
  */

trait UserTable { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val userTableQuery = TableQuery[UserTable]

  class UserTable(tag: Tag) extends Table[User](tag, "traineeuser") {
    val name = column[String]("username", O.SqlType("VARCHAR(200)"))
    val password = column[String]("userpassword", O.SqlType("PASSWORD"))
    val role = column[Boolean]("userrole", O.SqlType("BOOLEAN"))
    val id: Rep[Int] = column[Int]("userid", O.AutoInc, O.PrimaryKey)

    def * = (name, password, role,id) <>(User.tupled, User.unapply)

  }
}

@Singleton()
class UserRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UserTable with
  HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def insert(user: User) = db.run {
    userTableQuery += user
  }

  def validateUser(name: String, password: String) = {
    val result = userTableQuery.filter(_.name === name).filter(_.password === password).to[List].result
    db.run {
      result
    }
  }
}
/*
  def getUserType(name:String):Future[Boolean]={
    val result=userTableQuery.filter(_.name===name).to[List].result
    val finalResult= db.run{ result }
    finalResult.map(x => if( x(0).role == true) true else false)
  }
  */


//class UserRepo extends UserRepoApi with MyDBComponent
/*
object UserRepo extends UserRepoApi { this: DbComponent =>
  createTable
}
*/
