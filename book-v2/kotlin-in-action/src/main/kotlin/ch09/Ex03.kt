package ch09

fun main() {
    val strings: MutableList<Any> = mutableListOf("abc", "bac")
    addAnswer(strings)
}

fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}

fun test(i: Int) {
    val n: Number = i
}