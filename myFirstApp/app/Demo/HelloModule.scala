package Demo

import com.google.inject.AbstractModule
import com.google.inject.name.Names

class HelloModule extends AbstractModule {
  def configure() = {

    bind(classOf[Hello])
      .annotatedWith(Names.named("en"))
      .to(classOf[EnglishHello])

    bind(classOf[Hello])
      .annotatedWith(Names.named("he"))
      .to(classOf[HindiHello])
  }
}