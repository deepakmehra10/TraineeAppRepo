package controllers

import Demo.Hello
import com.google.inject.Inject
import com.google.inject.name.Named
import play.api.inject._
import play.api.mvc.{Action, Controller}

/**
  * Created by knoldus on 4/3/16.
  */
class HindiController @Inject() (@Named("he") hello: Hello) extends Controller{

def hindiHello=Action {

Ok(hello.sayHello("Namaste"))

}

}
