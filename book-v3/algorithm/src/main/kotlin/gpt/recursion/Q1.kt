package gpt.recursion

fun main() {
    println(v1(3))
    println(v2(3))
}

fun v1(n: Int): Int {
    if (n == 0) return 1
    return n * v1(n - 1)
}

fun v2(n: Int): Int {
    if (n <= 0) return 0

    val s = IntArray(n)
    var ptr = -1
    var n = n

    do {
        s[++ptr] = n
        n -= 1
    } while (n > 0)

    var result = 1
    for (i in 0..ptr) {
        result *= s[i]
    }

    return result
}