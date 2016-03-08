package controllers

import Demo.Hello
import com.google.inject.Inject
import com.google.inject.name.Named
import play.api.mvc.{Action, Controller}

/**
  * Created by knoldus on 4/3/16.
  */
class EnglishController @Inject() (@Named("en") hello: Hello) extends Controller{

  def englishHello=Action {

    Ok(hello.sayHello("hello"))

  }

}
