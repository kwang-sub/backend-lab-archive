package book.chap05.question

fun factorial(n: Int): Int {
    var result = 1
    for (i in n downTo 1) {
        result *= i
    }
    return result
}

fun gcd(x: Int, y: Int): Int {
    var tempX: Int = x
    var tempY: Int = y
    while (tempX % tempY != 0) {
        val whileY = tempY
        tempY = tempX % tempY
        tempX = whileY
    }

    return tempY
}

fun gcdArray(a: IntArray, start: Int, no: Int): Int {
    return if (no == 1) a[start]
    else if (no == 2) gcd(a[start], a[start + 1])
    else gcdArray(a, start + 1, no - 1)
}

fun hanoi(num: Int, x: Int, y: Int) {
    val i = 6 - x - y
    if (num > 1) hanoi(num - 1, x, i)
    println("원반[$num]을 ${change(x)} ${change(y)} 로 이동")
    if (num > 1) hanoi(num - 1, i, y)
}

private fun change(i: Int) = when (i) {
    1 -> "a"
    2 -> "b"
    3 -> "c"
    else -> throw IllegalArgumentException()
}

fun main() {
    hanoi(3, 1, 3)
}

