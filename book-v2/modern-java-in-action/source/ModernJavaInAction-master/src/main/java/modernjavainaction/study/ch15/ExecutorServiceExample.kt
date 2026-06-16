package modernjavainaction.study.ch15

import java.util.concurrent.Executors
import java.util.concurrent.Future

fun main() {
    val x = 1337

    val executorService = Executors.newFixedThreadPool(2)

    val y: Future<*> = executorService.submit { f(x) }
    val z: Future<*>  = executorService.submit { g(x) }

    println("" + y.get() + " " + z.get())

    executorService.shutdown()
}