package modernjavainaction.study.ch12

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters


fun main() {
    val now = LocalDate.now()
    val nextMonday = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
    println(nextMonday)

    val test = now.with(TemporalAdjusters.lastDayOfYear())
    println(test)
}