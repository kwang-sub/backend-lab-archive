package ch07.item45

import java.util.*

fun main() {
    val args = arrayOf("staple", "stapel")

    val groups = hashMapOf<String, MutableSet<String>>()

    for (s: String in args) {
        groups.computeIfAbsent(alphabetize(s)) {
            val treeSet: TreeSet<String> = TreeSet()
            treeSet.add(s)
            treeSet
        }
    }
    groups.forEach{(key, value) -> println("$key $value")}
}

fun alphabetize(s: String): String {
    return String(s.toCharArray().sortedArray())
}
