package ch06.atom73

fun testCode(code: Int) {
    if (code <= 1000) {
        throw IllegalArgumentException("'code' must be > 1000: $code")
    }
}

fun main() {
    try {
        testCode(161)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }

    try {
        testCode(0)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}