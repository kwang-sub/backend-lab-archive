package modernjavainaction.study.ch03

fun main() {
    val r1: () -> Unit = { println("Hi") }
    val r2: Runnable = Runnable { println("Hello world") }
    process(r1)
    process(r2)
    process { println("kwang") }
}

fun process(r: Runnable) {
    r.run()
}