package ch07

import java.time.LocalDate

fun main() {
    val person = Person("k", "s", 10)
    val person1 = Person("k", "s", 10)
    println(person < person1)
    println(person == person1)

    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(20, 30) in rect)
    println(Point(5, 30) in rect)

    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)
    println(vacation)

    val test = person..Person("k", "s", person.age + 10)


    val n = 9
    println((0..(n + 1)).forEach { println(it) })

    for (c in "str") {
        println(c)
    }
}

operator fun ClosedRange<LocalDate>.iterator() : Iterator<LocalDate> =
    object : Iterator<LocalDate> {
        var current = start
        override fun hasNext(): Boolean {
            return current <= endInclusive
        }

        override fun next(): LocalDate {
            return current.apply {
                current = plusDays(1)
            }
        }
    }

