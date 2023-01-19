package com.github.saeiddadkhah

import com.github.saeiddadkhah.entry.rest.finatra.CustomScalaObjectMapperModule
import com.github.saeiddadkhah.modules.CallbackModule
import com.github.saeiddadkhah.modules.ConfigModule
import com.github.saeiddadkhah.modules.ServiceModule
import com.google.inject.Module
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.filters.LoggingMDCFilter
import com.twitter.finatra.http.filters.TraceIdMDCFilter
import com.twitter.finatra.http.routing.HttpRouter

object Application extends HttpServer with ConfigModule {

  override def jacksonModule: Module = CustomScalaObjectMapperModule

  override protected def defaultHttpPort: String = config getString "application.server.port"

  override protected def modules: Seq[Module] = Seq(CallbackModule, ServiceModule)

  override protected def configureHttp(router: HttpRouter): Unit = {
    // Filters
    router.filter[CommonFilters]
    router.filter[LoggingMDCFilter[Request, Response]]
    router.filter[TraceIdMDCFilter[Request, Response]]
  }

}
