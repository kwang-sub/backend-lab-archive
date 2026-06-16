package question

fun main() {
    q5(intArrayOf(1, 2, 3, 4), IntArray(4))
}

fun q4(a: IntArray, b: IntArray) {
    println("a = ${a.joinToString()}")
    println("b = ${b.joinToString()}")

    println("copy")

    for (i in a.withIndex()) {
        b[i.index] = i.value
    }

    println("b = ${b.joinToString()}")
}

fun q5(a: IntArray, b: IntArray) {
    println("a = ${a.joinToString()}")
    println("b = ${b.joinToString()}")

    println("copy")

    for (i in a.withIndex()) {
        b[b.size - 1 - i.index] = i.value
    }

    println("b = ${b.joinToString()}")
}