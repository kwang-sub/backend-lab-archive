package modernjavainaction.study.ch06

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.TearDown
import java.util.concurrent.TimeUnit
import java.util.stream.Stream

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(2, jvmArgs = ["-Xms4G", "-Xmx4G"])
class ParallelStreamBenchmark {

    @Benchmark
    fun sequentialSum(): Long {
        return Stream.iterate(1L) { it + 1 }
            .limit(N)
            .reduce(0L) { one, two -> one + two }
    }

    @TearDown(Level.Invocation)
    fun tearDown() {
        System.gc()
    }

    companion object {
        val N = 10_000_000L
    }
}