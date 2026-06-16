package ch03.atom37

fun main() {
    val s1 = "abc"
    val s3: String? = null

    val map = mapOf(0 to "yes", 1 to "no")
    val first: String? = map[0]
    val second: String? = map[2]
    println(first)
    println(second)
}