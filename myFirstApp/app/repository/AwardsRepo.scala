package repository

import javax.inject.{Inject, Singleton}



import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile


import scala.concurrent.Future

/**
  * Created by knodus on 9/3/16.
  */

case class Awards(name:String,details:String,year:Int,sno:Int=0)

trait AwardTable { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val awardTableQuery = TableQuery[AwardTable]

  class AwardTable(tag: Tag) extends Table[Awards](tag, "awards") {

    val name = column[String]("name", O.SqlType("VARCHAR(200)"))
    val details = column[String]("details",O.SqlType("VARCHAR(200)"))
    val year= column[Int]("year")
    val sno = column[Int]("sno",O.AutoInc,O.PrimaryKey)

    def * = (name,details,year,sno) <>(Awards.tupled, Awards.unapply)

  }
}

@Singleton()
class AwardRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends AwardTable with
  HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def getAllAwards():Future[List[Awards]]={
    val statement=awardTableQuery.to[List].result
    db.run(statement)
  }

  def getUserAwards(student:String):Future[List[Awards]]={
    val statement=awardTableQuery.filter(_.name===student).to[List].result
    db.run(statement)
  }

  def addAwards(award:Awards)={
    val statement=awardTableQuery += award
    db.run(statement)
  }

}
