package ch04.item16

class Time(
    val hour: Int,
    val minute: Int,
) {
    init {
        if (hour < 0 || hour >= HOURS_PER_DAY)
            throw IllegalArgumentException("시간: $hour")
        if (minute < 0 || minute > MINUTES_PER_HOUR)
            throw IllegalArgumentException("qns: $minute")
    }

    companion object {
        const val HOURS_PER_DAY = 24
        const val MINUTES_PER_HOUR = 60
    }
}