package ch06

import strings.joinToString


fun main(args: Array<String>) {
    for (i in args.indices) {
        println("Argument $i is: ${args[i]}")
    }

    val arrayOfNulls = arrayOfNulls<String>(10)

    val array = Array(10) { i -> ('a' + i).toString() }
    println(array.joinToString(" "))

    val strings = listOf("a", "b", "c")
    val toTypedArray = strings.toTypedArray()
    println("%s/%s/%s".format(*toTypedArray))


    val fiveZeros = IntArray(5)
    val fiveZerosToo = intArrayOf(1, 2, 3, 4)

    val intArray = IntArray(5) { i -> i * 10 }
    println(intArray.joinToString())

    println(strings.joinToString())

    intArray.forEachIndexed { index, i ->
        println("$index  $i")

    }

}