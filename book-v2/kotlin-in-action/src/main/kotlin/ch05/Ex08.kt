package ch05

import java.io.File

fun main() {
    val books = listOf<Book>(
        Book("title1", listOf("kwang1", "sub1")),
        Book("title2", listOf("kwang2", "sub2")),
        Book("title3", listOf("kwang3", "sub3")),
    )

    val toSet = books.flatMap { it.authors }.toSet()
    println(toSet)

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())

    val file = File("/kwang/.hiddenDir/a.txt")
    println(file.isInsideHiddenDirectory())
}

fun File.isInsideHiddenDirectory() =
    generateSequence(this) {it.parentFile}.any {it.isHidden}