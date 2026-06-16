package ch07.item42

import java.util.Collections


fun main() {
    var words = listOf("zzzz", "asdf")
    Collections.sort(words, Comparator { s1, s2 ->
        return@Comparator s1.length.compareTo(s2.length)
    })
    println(words)
    words = words.sortedWith(compareBy({ it.length }, { it }))
    println(words)
}
