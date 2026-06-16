package ch06.atom73

fun toss(which: Int) = when (which) {
    1 -> throw Exception1(1)
    2 -> throw Exception2("Exception 2")
    3 -> throw Exception3("Exception 3")
    else -> "OK"
}

fun test(which: Int): Any? =
    try {
        toss(which)
    } catch (e: Exception1) {
        e.value
    } catch (e: Exception3) {
        e.message
    } catch (e: Exception2) {
        e.message
    }

fun main() {
    println(test(0))
    println(test(1))
    println(test(2))
    println(test(3))
}