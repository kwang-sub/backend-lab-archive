package item01

import kotlin.concurrent.thread
import kotlin.properties.Delegates

fun main() {
    val list1: MutableList<Int> = mutableListOf()
    var list2: List<Int> = listOf()

    list1.add(1)
    list2 = list2 + 1

    var list = listOf<Int>()
    for (i in 1..1000) {
        thread {
            list = list + i
            list1.add(1)
        }
    }
    Thread.sleep(1000)
    println(list.size)
    println(list1.size)

    var names by Delegates.observable(listOf<String>()) { _, old, new ->
        println("Names changed from $old to $new")
    }

    names += "Fabio"
    names += "Bill"
}