package modernjavainaction.study.ch15

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

fun main() {
    val executorService = Executors.newFixedThreadPool(10)
    val x = 1337

    val a = CompletableFuture<Int>()
    val b = CompletableFuture<Int>()
    executorService.submit() { a.complete(f(x)) }
    executorService.submit() { b.complete(f(x)) }

    val c = a.thenCombine(b, Math::addExact)

    println(c.get())
    executorService.shutdown()
}