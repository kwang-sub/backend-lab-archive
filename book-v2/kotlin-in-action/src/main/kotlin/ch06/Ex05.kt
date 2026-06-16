package ch06

fun main() {
    yellAy((Person4(null)))
}

fun yellAy(person: Person4) {
    println((person.name ?: "Anyone")+ "!!!")
    var s: String = person.name
    var s1: String? = person.name
}