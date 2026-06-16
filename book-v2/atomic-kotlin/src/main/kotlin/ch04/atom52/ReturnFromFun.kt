package ch04.atom52

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    val value = 3
    var result = ""
    list.forEach {
        result += "$it"
        if (it == value) {
            println(result == "123")
            return@forEach
        }
    }
    println("Never gets here")
}