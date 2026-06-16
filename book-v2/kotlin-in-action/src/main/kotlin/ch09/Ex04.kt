package ch09

import java.util.Random

fun main() {
    val ints = mutableListOf(1, 2, 3)
    val anyItems = mutableListOf<Any>()
    copyData(ints, anyItems)
    println(anyItems)

    val list: MutableList<Any?> = mutableListOf('c', 1, "qwe")
    val chars = mutableListOf('a', 'b', 'c')
    val unknownElements: MutableList<*> = if (Random().nextBoolean()) list else chars
//    unknownElements.add(42)
    println(unknownElements.first())
    printFirst(listOf("kwang", "sub"))
}

fun printFirst(list: List<*>) {
    if (list.isNotEmpty()) {
        println(list.first())
    }
}


fun <T> copyData(source: MutableList<T>, destination: MutableList<in T>) {
    for (item in source) {
        destination.add(item)
    }
}