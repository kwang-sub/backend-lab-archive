package ch07.atom79

val characters: List<String> = buildList {
    add("Chars: ")
    ('a'..'d').forEach { add("$it") }
}

val charMap: Map<Char, Int> = buildMap {
    ('A'..'F').forEachIndexed { index, c -> put(c, index) }
}

fun main() {
    println(characters)
    println(charMap)
}