package controllers

import play.api.mvc.{Action, Controller}

/**
  * Created by knoldus on 25/2/16.
  */
class MyController extends Controller {

def greet(name:String)=Action{
  Ok("Welcome " + name)

}

}
