package connection

import slick.driver.PostgresDriver

trait MyDBComponent extends DbComponent {

  val driver = PostgresDriver

  import driver.api._

  val db: Database = MyPostgresDB.connectionPool

}

private[connection] object MyPostgresDB{

  import slick.driver.PostgresDriver.api._

  val connectionPool = Database.forConfig("mydb")

}
