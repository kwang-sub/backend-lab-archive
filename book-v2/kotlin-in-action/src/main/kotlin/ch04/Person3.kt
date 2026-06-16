package ch04

import java.lang.management.ManagementFactory

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}
class Person3(val name: String) {
    companion object : JSONFactory<Person3> {
        override fun fromJSON(jsonText: String): Person3 = Person3(jsonText)
    }

}

fun <T> loadFromJSON(factory: JSONFactory<T>): T {
    return factory.fromJSON(factory.toString())
}