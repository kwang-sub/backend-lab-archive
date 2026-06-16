package ch03.item14

import java.util.*

fun main() {
    val args = arrayOf("test", "aaa")
    val s: TreeSet<String> = TreeSet()
    Collections.addAll(s, *args)
    println(s)
}

val hashCodeOrder = Comparator<Any> { o1, o2 ->
    o1.hashCode() - o2.hashCode()
}

val hashCodeOrder2 = Comparator<Any> { o1, o2 ->
    o1.hashCode().compareTo(o2.hashCode())
}

val hashCodeOrder3: Comparator<Any> =
    Comparator.comparingInt { it.hashCode() }