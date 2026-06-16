package ch08.item52

import java.util.*

fun main() {
    val set = TreeSet<Int>()
    val list = mutableListOf<Int>()

    for (i in -3..3) {
        set.add(i)
        list.add(i)
    }

    for (i in 0..3) {
        set.remove(i)
        list.remove(i)
    }

    println(set)
    println(list)
}