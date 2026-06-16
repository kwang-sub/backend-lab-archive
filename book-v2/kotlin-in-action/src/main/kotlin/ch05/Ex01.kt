package ch05

fun main() {
    val people = listOf<Person>(Person("Alice", 29), Person("Bob", 28))
    findTheOldest(people)
    println(people.maxBy { it.age })
    println(people.maxBy { person -> person.age })

    val names = people.joinToString(separator = " | ") { it.name }
    println(names)

    val getAge = Person::age

    println("==============")
    println(people.maxBy(getAge))
    println(people.maxBy { p -> p.age })
}

fun findTheOldest(people: List<Person>) {
    var maxAge = Int.MIN_VALUE
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}