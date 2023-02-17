package com.github.saeiddadkhah.entry.rest.finatra.adapter.auth.api

import com.twitter.finatra.http.annotations.RouteParam

case class SignInRequestDTO(@RouteParam("username") username: String, password: String)
