package ch08

import java.io.BufferedReader
import java.io.FileReader

fun main() {
    val people = listOf(Person2("Alice", 29), Person2("Bob", 31))
    println(people.filter { it.age < 30 })

    val result = mutableListOf<Person2>()
    for (person in people) {
        if (person.age < 30) result.add(person)
    }
    println(result)

    lookForAlice(people)

}

fun lookForAlice(people: List<Person2>) {
    people.forEach (
        fun(person) {
            if(person.name == "Alice") {
                println("Alice")
                return
            }
            println("${person.name}  is Not Alice")
        }
    )
    println("Alice is not found")
}

fun readFirstLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use {
        return it.readLine()
    }
}