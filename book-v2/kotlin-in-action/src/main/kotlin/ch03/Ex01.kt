package ch03

fun main() {
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 2 to "two")

    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)
    println(map::class)

    println(set.max())
    println(list.last())

    println(list)
}