package ch07.atom79

private fun messy(): String {
    val built = StringBuilder()
    built.append("ABCs: ")
    ('a'..'x').forEach { built.append(it) }
    return built.toString()
}

private fun clean() = buildString {
    append("ABCs: ")
    ('a'..'x').forEach { append(it) }
}

private fun cleaner() = ('a'..'x').joinToString("", "ABCs: ")

fun main() {
    println(messy())
    println(clean())
    println(cleaner())
}