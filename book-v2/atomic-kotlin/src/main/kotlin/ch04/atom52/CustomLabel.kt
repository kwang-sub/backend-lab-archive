package ch04.atom52

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    val value = 3
    var result = ""
    list.forEach tag@ {
        result += "$it"
        if (it == value) {
            println(result)
            return@tag
        }
    }
    println("12345")
}