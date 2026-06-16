package ch04

import ch03.test


fun main() {
    val user3: User3 = User3("Alice")
    user3.address = "kwang"
    println(user3.test)
    println(user3.address)
}