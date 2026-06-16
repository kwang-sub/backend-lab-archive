package ch04.atom48

fun <T> List<T>.any(
    predicate: (T) -> Boolean
): Boolean {
    for (element in this) {
        if (predicate(element)) {
            return true
        }
    }
    return false
}

fun main() {
    val ints = listOf(1, 2, -3)
    println(ints.any { it == -3 })

    repectV2(5) { println(it + 1)}
}


fun repectV2(n: Int, action: (Int) -> Unit) {
    for (i in 0 until n) {
        action(i)
    }
}
