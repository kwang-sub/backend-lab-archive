package modernjavainaction.study.ch09

fun main() {
    val test = Test()
    test.r1.run()
    test.r2.run()

    doSomething { println("fas") }
    doSomething(Runnable { println("afa") })
}

class Test(
    val a: Int = 10
) {
    val r1 = object : Runnable {
        override fun run() {
            val a = 12
            println("$a r1 Hello")
            println(this.javaClass)
        }
    }

    val r2 = Runnable {
        val a = 10
        println("$a r2 Hello")
        println(this.javaClass)
    }
}

fun interface Task {
    fun execute()
}

fun doSomething(r: Runnable) {
    r.run()
    println("runnable")
}

fun doSomething(r: Task) {
    r.execute()
    println("task")
}

