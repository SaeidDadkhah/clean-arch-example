package com.github.saeiddadkhah.entry.rest.finatra.adapter.common

import com.github.saeiddadkhah.entry.rest.finatra.adapter.common.api._

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object CommonDTOFactory {

  def zonedDateTime: ZonedDateTime => ZonedDateTimeDTO = o =>
    o format DateTimeFormatter.ISO_ZONED_DATE_TIME

}
