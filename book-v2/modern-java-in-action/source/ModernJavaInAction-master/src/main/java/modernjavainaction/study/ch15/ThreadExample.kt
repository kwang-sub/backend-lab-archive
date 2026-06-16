package modernjavainaction.study.ch15

fun main() {
    val x: Int = 1337
    val result = Result()

    val t1 = Thread() { result.left = f(x) }
    val t2 = Thread() { result.right = g(x) }

    t1.start()
    t2.start()

    t1.join()
    t2.join()
    val plus = result.left?.let { result.right?.plus(it) }
    println(plus)
}

class Result(
    var left: Int? = null,
    var right: Int? = null,
)

fun f(x: Int) = x

fun g(x: Int) = x