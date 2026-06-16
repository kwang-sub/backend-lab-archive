package modernjavainaction.study.ch08

import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class Ex01 {

    @Test
    fun concurrentHashMap() {
        val map = ConcurrentHashMap<String, Long>()
        val parallelismThreshold = 1L
        val maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold) { t, u -> if (t >= u) t else u })
        println(maxValue)
    }
}