package repository

import javax.inject.{Inject, Singleton}

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile


import scala.concurrent.Future

/**
  * Created by knodus on 9/3/16.
  */

case class Assignment(studname:String,name:String,date:String,marks:Int,remarks:String,sno:Int=0)

trait AssignmentTable { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val assignmentTableQuery = TableQuery[AssignmentTable]

  class AssignmentTable(tag: Tag) extends Table[Assignment](tag,"assignment") {
    val studname = column[String]("studname", O.SqlType("VARCHAR(200)"))
    val name = column[String]("name", O.SqlType("VARCHAR(200)"))
    val date = column[String]("date",O.SqlType("VARCHAR(50)"))
    val marks= column[Int]("marks")
    val remarks = column[String]("remarks", O.SqlType("VARCHAR(200)"))
    val sno = column[Int]("sno",O.PrimaryKey, O.AutoInc)

    def * = (studname,name,date,marks,remarks,sno) <>(Assignment.tupled, Assignment.unapply)

  }
}

@Singleton()
class AssignmentRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends AssignmentTable with
  HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def getAllAssignment:Future[List[Assignment]]={
    val statement=assignmentTableQuery.to[List].result
    db.run(statement)
  }

  def getUserAssignment(student:String):Future[List[Assignment]]={
    val statement=assignmentTableQuery.filter(_.studname===student).to[List].result
    db.run(statement)
  }

  def addAssignment(assignment:Assignment)={
    val assign=Assignment(assignment.studname,assignment.name,assignment.date,assignment.marks,assignment.remarks)
    val statement=assignmentTableQuery += assign
    db.run(statement)
  }



}
