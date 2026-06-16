package ch01.atom06

fun multipleByTwo(x: Int): Int {
    println("Inside multiplyByTwo")
    return x * 2
}

fun main() {
    val r = multipleByTwo(2)
    println(r)
}