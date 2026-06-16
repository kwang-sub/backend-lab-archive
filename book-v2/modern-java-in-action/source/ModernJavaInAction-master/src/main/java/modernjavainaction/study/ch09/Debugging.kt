package modernjavainaction.study.ch09

fun main() {
    times(3) { println("hello") }
//    curriedTimes(3){ println("test")}
}

fun times(i: Int, f: () -> Unit) {
    f()
    if (i > 1) times(i - 1, f)
}

fun curriedTimes(i: Int): (() -> Unit) -> Unit {
    return { f ->
        repeat(i) {
            f()
        }
    }
}