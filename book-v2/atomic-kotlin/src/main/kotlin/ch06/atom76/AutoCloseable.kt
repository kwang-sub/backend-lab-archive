package ch06.atom76

import java.io.File

fun main() {
    val use = File("Results.txt")
        .bufferedReader()
        .use { it.readLine().first() }
    println(use)
}