package item01

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


/**
 * 멀티스레드 테스트
 */
fun main1() {
    var num = 0
    for (i in 1..10000) {
        thread {
            Thread.sleep(10)
            num += 1
        }
    }
    Thread.sleep(5000)
    println(num)
}

/**
 * 코루틴 활용 멀티스레스 테스트
 */
suspend fun main2() {
    var num = 0
    coroutineScope {
        for (i in 1..10000) {
            launch {
                delay(10)
                num += 1
            }
        }
    }
    println(num)
}

/**
 * 락을 이용한 가변성 제한
 */
fun main() {
    val lock = Any()
    var num = 0
    for (i in 1..1000) {
        thread {
            Thread.sleep(10)
            synchronized(lock) {
                num += 1
            }
        }
    }
    Thread.sleep(1000)
    println(num)
}