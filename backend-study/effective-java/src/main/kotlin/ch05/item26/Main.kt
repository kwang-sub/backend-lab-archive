package ch05.item26


fun main() {
    val strings = mutableListOf<String>()
    unsafeAdd(strings, 42.toString())
    val s: String = strings[0]
    println(s)
}

fun unsafeAdd(list: MutableList<String>, o: String) {
    list.add(o)
}
