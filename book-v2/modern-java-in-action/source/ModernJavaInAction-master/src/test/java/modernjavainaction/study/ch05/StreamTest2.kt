package modernjavainaction.study.ch05

import modernjavainaction.base.chap04.Dish
import modernjavainaction.study.ch04.menu
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.Charset
import java.nio.file.Files
import java.util.function.IntSupplier
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.io.path.Path
import kotlin.math.sqrt
import kotlin.streams.toList

class StreamTest2 {
    @Test
    fun 숫자형_스트림() {
        val reduce = menu.stream().map { it.calories }.reduce(0, Integer::sum)
        val sum = menu.sumOf { it.calories }

        assertThat(reduce).isEqualTo(sum)
    }

    @Test
    fun 특화스트림_복원() {
        val intStream = menu.stream().mapToInt(Dish::getCalories)
        val boxed = intStream.boxed()

        val maxCalories = menu.stream().mapToInt(Dish::getCalories)
            .max().orElseGet { 0 }

        val rangeClosed = IntStream.rangeClosed(1, 100).toList()
        val range = IntStream.range(1, 100).toList()

        assertThat(rangeClosed.size).isEqualTo(100)
        assertThat(range.size).isEqualTo(99)
    }

    @Test
    fun 피타고라스() {
        val a = 10
        IntStream.rangeClosed(1, 100)
            .mapToObj { Triple(a, it, sqrt(((a * a + it * it).toDouble()))) }
            .filter { it.third % 1 == 0.0 }
            .forEach(System.out::println)
    }

    @Test
    fun 파일스트림() {
        var uniqueWords: Long = 0

        val lines = Files.lines(Path("data.txt"), Charset.defaultCharset())
        lines.use { lines ->
            uniqueWords = lines.flatMap { it.split(" ").stream() }.distinct().count()
        }
        println(uniqueWords)
    }

    @Test
    fun 무한스트림() {
        Stream.iterate(0) { n -> n + 2 }
            .limit(10)
            .forEach(System.out::println)
    }

    @Test
    fun 퀴즈_5_4() {
        Stream.iterate(listOf(0, 1), ){ listOf(it[1], it[0] + it[1] ) }
            .limit(20)
            .forEach{ println("${it[0]} ${it[1]}")}
    }

    @Test
    fun 무한스트림2() {
        Stream.generate(Math::random)
            .limit(5)
            .forEach(System.out::println)
    }
}