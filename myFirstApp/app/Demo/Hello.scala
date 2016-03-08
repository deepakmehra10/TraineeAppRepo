package Demo

/**
  * Created by knoldus on 4/3/16.
  */
trait Hello {

  def sayHello(name:String):String

}

class EnglishHello extends Hello {
  def sayHello(name: String) = "Hello " + name
}

class HindiHello extends Hello {
  def sayHello(name: String) = "Hello " + name
}
