package selenium

import java.util.concurrent.TimeUnit

import org.openqa.selenium.firefox.FirefoxDriver
import org.scalatest.FlatSpec
import org.scalatest.tagobjects.FirefoxBrowser


/**
  * Created by akshay on 14/3/16.
  */
class UserControllerIntegrationSpec extends FlatSpec {

  val port = 9000
  val logUrl = "http://localhost:" + port + "/users/login"
  val driver = new FirefoxDriver()
  "Admin " should "successfully hit the url" in {

    driver.get(logUrl)
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

  }

  "User" should "log in successfully" in {

    driver.findElementById("username").sendKeys("sangeeta")
    driver.findElementById("password").sendKeys("123")
    driver.findElementByCssSelector(".form-group .btn").click()

  }

  "User" should "display his/her award table" in {
    driver.findElementById("awards").click()
  }

  "User" should "display his/her language table" in {
    driver.findElementById("language").click()
  }

  "User" should "display his/her assignment table" in {
    driver.findElementById("assignment").click()
  }


  "User" should "display his/her programming language table" in {
    driver.findElementById("programming").click()
  }

  "User" should "logout successfully" in {

    driver.findElementByCssSelector(".col-md-1 .btn").click()

  }

  "Admin" should "log in successfully" in {

    driver.findElementById("username").sendKeys("admin")
    driver.findElementById("password").sendKeys("admin")
    driver.findElementByCssSelector(".form-group .btn").click()

  }

  "Admin" should "display all the user's award table" in{
    driver.findElementById("awards").click()
  }

  "Admin" should "display all the user's language table" in{
    driver.findElementById("language").click()
  }

  "Admin" should "display all the user's assignment table" in{
    driver.findElementById("assignment").click()
  }


  "Admin" should "display all the user's programming language table" in{
    driver.findElementById("programming").click()
  }

  "Admin" should "display all the intern's table" in{
    driver.findElementById("intern").click()
  }


  "Admin" should "Add record in intern's table" in{

    driver.findElementByCssSelector("#menu5 .btn").click()
    driver.findElementByCssSelector("#myModal4 #name").sendKeys("TestName")
    driver.findElementByCssSelector("#myModal4 #email").sendKeys("testEmail@t.c")
    driver.findElementByCssSelector("#myModal4 #mobile").sendKeys("65465")
    driver.findElementByCssSelector("#myModal4 #award").sendKeys("testAward")
    driver.findElementByCssSelector("#myModal4 #internadd").click()
    driver.findElementByCssSelector("#myModal4 .modal-footer .btn").click()

  }


  "Admin" should "logout successfully" in {

    driver.findElementByCssSelector(".col-md-1 .btn").click()

  }


}