package ch06

fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

data class Student(
    var name: String?
)

fun main() {
//    fail("test")
    val student = Student(null)
    val name = student.name ?: fail("예외던짐")
    println(name)
}

