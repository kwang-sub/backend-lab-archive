package modernjavainaction.study.ch15

import modernjavainaction.base.chap15.ScheduledExecutorServiceExample
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    val scheduledExecutorService = Executors.newScheduledThreadPool(1)
    work1()

    scheduledExecutorService.schedule(ScheduledExecutorServiceExample::work2, 10, TimeUnit.SECONDS)

    scheduledExecutorService.shutdown()
}

fun work1() {
    println("work1")
}


fun work2() {
    println("work2")
}