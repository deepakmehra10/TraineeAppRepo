package repository

import javax.inject.{Inject, Singleton}

import connection.DbComponent
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile


import scala.concurrent.Future

/**
  * Created by knodus on 9/3/16.
  */
case class Language(sno:Int,name:String,fluency:String)

trait LanguageTable { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val languageTableQuery = TableQuery[LanguageTable]

  class LanguageTable(tag: Tag) extends Table[Language](tag, "language") {
    val sno = column[Int]("sno")
    val name = column[String]("name", O.SqlType("VARCHAR(200)"))
    val fluency = column[String]("fluency",O.SqlType("VARCHAR(200)"))

    def * = (sno,name,fluency) <>(Language.tupled, Language.unapply)

  }
}

@Singleton()
class LanguageRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends LanguageTable with
  HasDatabaseConfigProvider[JdbcProfile] {
  this: DbComponent =>

  import driver.api._

  def getAllLanguage():Future[List[Language]]={
    val statement=languageTableQuery.to[List].result
    db.run(statement)
  }

  def addLanguage(lang:Language)={
    val statement=languageTableQuery += lang
    db.run(statement)
  }

}

