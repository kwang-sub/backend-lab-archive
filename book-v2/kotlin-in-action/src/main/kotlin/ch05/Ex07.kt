package ch05

fun main() {
    val canBeInClub27 = { p: Person -> p.age <= 27 }
    val people = listOf(Person("kwang", 30), Person("sub", 12))
    println(people.all(canBeInClub27))

    println(people.any(canBeInClub27))

    println(people.count(canBeInClub27))

    val groupBy = people.groupBy { it.age }
    println(groupBy.get(30))

    val result = people.asSequence()
        .map(Person::name)
        .filter { it.startsWith("k") }
        .toList()

    println(listOf(1, 2, 3, 4).asSequence().map { it * it }.find { it > 3 })

    val filterFirst = people.filter { it.name.length > 3 }
        .map { it.name }

    val mapFirst = people.map { it.name }
        .filter { it.length > 3 }

    println("=================")
    println(filterFirst)
    println(mapFirst)
}