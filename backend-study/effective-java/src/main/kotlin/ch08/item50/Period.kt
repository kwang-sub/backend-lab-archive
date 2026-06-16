package ch08.item50

import java.time.Instant
import java.util.Date

class Period(
    val start: Date,
    val end: Date,
) {
    init {
        if (start > end) {
            throw IllegalArgumentException("$start after $end")
        }
    }
}


fun main() {
    val p = Period(Date.from(Instant.now()), Date.from(Instant.now()))
    println(p.end)
    p.end.year = 0
    println(p.end)

}