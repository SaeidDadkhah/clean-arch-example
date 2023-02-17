package com.github.saeiddadkhah

import com.github.saeiddadkhah.entry.rest.finatra._
import com.github.saeiddadkhah.entry.rest.finatra.controller._
import com.github.saeiddadkhah.entry.rest.finatra.filters.AuthorizationFilter
import com.github.saeiddadkhah.modules._
import com.google.inject.Module
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters._
import com.twitter.finatra.http.routing.HttpRouter

object Application extends HttpServer with ConfigModule {

  override def jacksonModule: Module = CustomScalaObjectMapperModule

  override protected def defaultHttpPort: String = s":${config getString "application.server.port"}"

  override protected def modules: Seq[Module] = Seq(CallbackModule, ServiceModule)

  override protected def configureHttp(router: HttpRouter): Unit = {
    // Filters
    router.filter[CommonFilters]
    router.filter[LoggingMDCFilter[Request, Response]]
    router.filter[TraceIdMDCFilter[Request, Response]]

    // Controllers
    // // Unauthorized Access
    router.add[unsafe.AuthenticationController]
    // // Authorized Access
    router.add[AuthorizationFilter, AuthenticationController]
    router.add[AuthorizationFilter, BlogController]
  }

}
