package ch07.atom81

fun main() {
    val strings = listOf("a", "b", "c")
    val all: List<Any> = listOf(1, 2, "x")
    useList(strings)
    useList(all)
}

fun useList(list: List<Any>) {
//    if (list is List<String>){}
}