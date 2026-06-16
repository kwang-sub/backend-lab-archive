package ch05

fun main() {
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 })

    println(list.map { it * it })

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.filter { it.age > 30 })

    println(people.map { it.name })
    println(people.map(Person::age))

    println(people.filter { it.age > 30 }.map(Person::age))

    println(people.maxBy { it.age })

    val maxAge = people.maxOf { it.age }
    println(people.filter { it.age == maxAge })

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.uppercase() })
}