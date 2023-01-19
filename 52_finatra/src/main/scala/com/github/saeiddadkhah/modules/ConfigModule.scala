package com.github.saeiddadkhah.modules

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

trait ConfigModule {

  lazy val config: Config = ConfigModule.config

}

object ConfigModule {

  private lazy val config: Config = ConfigFactory.load().withFallback(ConfigFactory.defaultApplication()).resolve

}
