package modernjavainaction.study.ch12

import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.temporal.ChronoField


fun main() {
    val now = LocalDateTime.now()

    val get = now.get(ChronoField.HOUR_OF_AMPM)
    println(get)
    println(now.hour)

    val ofEpochSecond = Instant.ofEpochSecond(3, 10)
    println(ofEpochSecond)
    val now2 = LocalDateTime.now()

    val between = Duration.between(now, now2)
    println(between)



    val twoOfDays = Period.ofDays(2)
    val dateNow = LocalDate.now()
    val tomorrow = twoOfDays.addTo(dateNow)
    println(tomorrow)
}