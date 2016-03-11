package repository

import javax.inject.{Inject, Singleton}

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile


import scala.concurrent.Future

/**
  * Created by knodus on 9/3/16.
  */

case class Interns(name:String,email:String,mobile:Int,award:String)

trait InternTable { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val internTableQuery = TableQuery[InternTable]

  class InternTable(tag: Tag) extends Table[Interns](tag, "intern") {
    val name = column[String]("name", O.SqlType("VARCHAR(200)"))
    val email = column[String]("email", O.SqlType("VARCHAR(200)"))
    val mobile = column[Int]("mobile")
    val award= column[String]("award",O.SqlType("VARCHAR(200)"))

    def * = (name,email,mobile,award) <>(Interns.tupled, Interns.unapply)

  }
}

@Singleton()
class InternRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends InternTable with
  HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def getAllInterns():Future[List[Interns]]={
    val statement=internTableQuery.to[List].result
    db.run(statement)
  }
/*
  def getUserInterns(student:Interns):Future[List[Interns]]={
    val statement=internTableQuery.filter(_.name===student).to[List].result
    db.run(statement)
  }
*/
  def addInterns(intern:Interns)={
    val statement=internTableQuery += intern
    db.run(statement)
  }

}
