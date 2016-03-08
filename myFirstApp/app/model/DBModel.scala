package model

import slick.driver.MySQLDriver.api._

case class Student(id:String,password:String)

trait StudentTable {

  class StudentTable(tag: Tag) extends Table[Student](tag, "student") {
    val id = column[String]("id", O.PrimaryKey, O.AutoInc)
    val password = column[String]("name", O.SqlType("Varchar(20)"))
    val db = Database.forConfig("mysql")
    val studentTable = TableQuery[StudentTable]


    def * = (id,password) <>(Student.tupled, Student.unapply)
  }

  val db = Database.forConfig("mysql")
  val studentTable = TableQuery[StudentTable]


}


object StudentRepo extends StudentTable with App {



  def createStudent() =
  {
    val createStatement=studentTable.schema.create
  db.run(createStatement)
  }

  StudentRepo.createStudent()

  /* def insertStudent(id: Int,name: String, address: String) = {
    val insertStatement = studentTable.returning(studentTable.map(_.id)) += Student(id, name, address)
    db.run(insertStatement)
  }


  def getAllStudent() = {
    db.run {
      studentTable.to[List].result
    }
  }
*/

}
