package ch04.atom50

class Person(
    var name: String,
    val age: Int,
) {
}

val names = listOf("Alice", "Alice", "kl", "gg")

val ages = listOf(1, 2, 2, 3)

fun people(): List<Person> =
    names.zip(ages, ::Person)


fun main() {
    val result = people().associateBy { it.name }
    println(result)
    val person = Person("kwang", 30)

    result.mapKeys {(key, _) -> println(key) }
}