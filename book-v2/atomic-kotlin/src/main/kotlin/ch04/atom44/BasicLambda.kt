package ch04.atom44


fun main() {
    val list = listOf(1, 2, 3, 4)
    val result = list.map { "[$it]" }

    println(result)

    val list2 = listOf('a', 'b', 'c')
    val result2 = list2.map { "[${it.uppercase()}]" }
    println(result2)

    list2.mapIndexed { index, c -> println("$index  $c") }
}