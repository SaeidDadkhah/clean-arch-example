package com.github.saeiddadkhah.entry.rest.finatra.adapter.common

import com.github.saeiddadkhah.entry.rest.finatra.adapter.common.api._

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object CommonFactory {

  def zonedDateTime: ZonedDateTimeDTO => ZonedDateTime = dto =>
    ZonedDateTime.parse(dto, DateTimeFormatter.ISO_ZONED_DATE_TIME)

}
