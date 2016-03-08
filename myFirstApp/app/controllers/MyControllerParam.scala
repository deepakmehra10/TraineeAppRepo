package controllers

import play.api.mvc.{Controller, Action}

/**
  * Created by knoldus on 26/2/16.
  */
class MyControllerParam extends Controller{

  def twoParam(firstname:String,age:Int)=Action{
    Ok("<h1 style=\"color:gray;\">Hi my name is "+ firstname + " and age is " + age + "</h1>").as("text/html")
  }

}
