package com.github.saeiddadkhah.entry.rest.finatra

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.twitter.finatra.jackson.modules.ScalaObjectMapperModule

object CustomScalaObjectMapperModule extends ScalaObjectMapperModule {

  override val propertyNamingStrategy: PropertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE

}
