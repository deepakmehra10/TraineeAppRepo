package repository
import scala.concurrent.ExecutionContext.Implicits.global
import connection.{MyDBComponent, DbComponent}
import models.User
import scala.concurrent.Future

/**
  * Created by knodus on 8/3/16.
  */

trait UserTable { this: DbComponent =>

  import driver.api._

  val userTableQuery = TableQuery[UserTable]

  class UserTable(tag: Tag) extends Table[User](tag, "trainee_db") {
    val name = column[String]("username", O.SqlType("VARCHAR(200)"))
    val password = column[String]("userpassword", O.SqlType("PASSWORD"))
    val role = column[Boolean]("userrole", O.SqlType("BOOLEAN"))
    val id: Rep[Int] = column[Int]("userid", O.AutoInc, O.PrimaryKey)

    def * = (name, password, role,id) <>(User.tupled, User.unapply)
    val db=Database.forConfig("mydb")
  }
}


trait UserRepoApi extends UserTable {
  this: DbComponent =>

  import driver.api._

  def insert(user: User) = db.run {
    userTableQuery += user
  }

  def validateUser(name:String,password:String)={
        val result=userTableQuery.filter(_.name===name).filter(_.password===password).to[List].result
    db.run{   result   }
  }

 /* def validate(user: User): Future[Boolean] = {
    val list: Future[List[User]] = db.run {
      userTableQuery.to[List].result
    }
    val res = list.map { x => if (x.contains(user)) true
    else false
    }
    res
  }
  */



}

class UserRepo extends UserRepoApi with MyDBComponent
/*
object UserRepo extends UserRepoApi { this: DbComponent =>
  createTable
}
*/
