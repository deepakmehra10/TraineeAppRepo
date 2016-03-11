package repository

import javax.inject.{Inject, Singleton}

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile


import scala.concurrent.Future

/**
  * Created by knodus on 9/3/16.
  */
case class ProgrammingLanguage(sno:Int,studname:String,langname:String,fluency:String)

trait ProgrammingLanguageTable { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val progLangTableQuery = TableQuery[ProgrammingLanguageTable]

  class ProgrammingLanguageTable(tag: Tag) extends Table[ProgrammingLanguage](tag, "programming_language") {
    val sno = column[Int]("sno")
    val studname = column[String]("studname", O.SqlType("VARCHAR(200)"))
    val langname = column[String]("langname", O.SqlType("VARCHAR(200)"))
    val fluency = column[String]("fluency",O.SqlType("VARCHAR(200)"))

    def * = (sno,studname,langname,fluency) <>(ProgrammingLanguage.tupled, ProgrammingLanguage.unapply)

  }
}

@Singleton()
class ProgrammingLanguageRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends ProgrammingLanguageTable with
  HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def getAllProgLanguage:Future[List[ProgrammingLanguage]]={
    val statement=progLangTableQuery.to[List].result
    db.run(statement)
  }

  def getUserProgLanguage(student:String):Future[List[ProgrammingLanguage]]={
    val statement=progLangTableQuery.filter(_.studname===student).to[List].result
    db.run(statement)
  }

  def addProgLanguage(lang:ProgrammingLanguage)={
    val statement=progLangTableQuery += lang
    db.run(statement)
  }

}

