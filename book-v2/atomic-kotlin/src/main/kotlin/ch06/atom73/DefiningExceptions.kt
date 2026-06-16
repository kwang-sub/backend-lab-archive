package ch06.atom73

class Exception1(
    val value: Int
): Exception("wrong value: $value")

open class Exception2(
    description: String
): Exception(description)

class Exception3(
    description: String
): Exception2(description)

fun main() {
    println(Exception1(13))
    println(Exception3("error"))
}