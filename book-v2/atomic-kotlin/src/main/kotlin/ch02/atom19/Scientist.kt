package ch02.atom19

class Scientist(
    val name: String
) {
    override fun toString(): String {
        return "Scientist('$name')"
    }
}

fun main() {
    val zeep = Scientist("Zeep Xanflorp")
    println(zeep)
}