package ch06.atom73

data class Switch(
    var on: Boolean = false,
    var result: String = "OK"
)

fun testFinally(i: Int): Switch {
    val sw = Switch()
    try {
        sw.on = true
        when (i) {
            0 -> throw IllegalStateException()
            1 -> return sw
        }
    } catch (e: IllegalStateException) {
        sw.result = "exception"
    } finally {
        sw.on = false
    }
    return sw
}

fun main() {
    println(testFinally(0))
    println(testFinally(1))
    println(testFinally(2))
}