package modernjavainaction.study.ch12

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.TimeZone

fun main() {
    val zoneId = TimeZone.getDefault().toZoneId()

    val nowDate = LocalDate.now()
    val zoneNowDate = nowDate.atStartOfDay(zoneId)

    val nowDateTime = LocalDateTime.now()
    val zoneNowDateTime = nowDateTime.atZone(zoneId)
}