package modernjavainaction.study.ch12

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.time.temporal.TemporalAdjuster

class NextWorkingDay {
    companion object: TemporalAdjuster {
        override fun adjustInto(temporal: Temporal): Temporal {
            val dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK))
            var dayToAdd: Long = 1
            if (dayOfWeek == DayOfWeek.FRIDAY) dayToAdd = 3
            else if(dayOfWeek == DayOfWeek.SATURDAY) dayToAdd = 2
            return temporal.plus(dayToAdd, ChronoUnit.DAYS)
        }
    }
}

fun main() {
    val now = LocalDate.now()
    val nextWorkingDay = NextWorkingDay.adjustInto(now)
    println(nextWorkingDay)
}