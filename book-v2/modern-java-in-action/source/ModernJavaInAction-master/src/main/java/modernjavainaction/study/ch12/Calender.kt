package modernjavainaction.study.ch12

import akka.japi.Predicate
import java.time.LocalDate
import java.time.chrono.JapaneseDate
import java.util.*

fun main() {
    val now = LocalDate.now()
    val japanese = JapaneseDate.from(now)

    val calender = Calendar.getInstance()
    val target = mutableListOf(1, 2, 3)
    val removeIf = target.removeIf { it == 1 }
    println(target)
    println(removeIf)
}

fun <T> MutableCollection<T>.removeIf(filter: Predicate<in T>): Boolean {
    val target = this.filter { filter.test(it) }.toSet()
    this.removeAll(target)
    return target.isNotEmpty()
}