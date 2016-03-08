package connection

import slick.driver.JdbcProfile



trait DbComponent {

  val driver: JdbcProfile

  import driver.api._

  val db: Database

}

