package modernjavainaction.study.ch07

import org.junit.jupiter.api.Test
import java.util.stream.LongStream

class Ex01 {

    @Test
    fun 병렬스트림() {
        val sequentialSum = sequentialSum(44444444L)
        println(sequentialSum)

        val parallelSum = parallelSum(44444444L)
        println(parallelSum)
    }

    fun sequentialSum(n: Long): Long {
        return LongStream.iterate(1L) { it + 1L }
            .limit(n)
            .reduce(0L) { left, right -> left + right }
    }

    fun parallelSum(n: Long): Long {
        return LongStream.iterate(1L) { it + 1L }
            .limit(n)
            .reduce(0L) { left, right -> left + right }
    }
}