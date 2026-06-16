package ch04.atom49

class Book(
    val title: String,
    val authors: List<String>
) {
}

fun main() {
    val books = listOf(
        Book("1984", listOf("g")),
        Book("1984", listOf("a"))
    )

    val result = books.flatMap { it.authors }
    println(result)
}