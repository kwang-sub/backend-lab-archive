package ch09

import java.security.Provider.Service
import java.util.ServiceLoader

fun main() {
    test(listOf("1", "2"))
    println(isA<String>("abc"))
    println(isA<String>(123))

    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>())
    val serviceImpl = loadService<Service>()
}

inline fun <reified T> loadService(): ServiceLoader<T> {
    val test: T
    return ServiceLoader.load(T::class.java)
}

fun test(list: List<*>) {
    val intList = list as? List<Int> ?: throw IllegalArgumentException("예외던짐")

}

inline fun <reified T> isA(value: Any) = value is T

inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> {
    val destination = mutableListOf<T>()
    for (element in this) {
        if (element is T) {
            destination.add(element)
        }
    }
    return destination
}
