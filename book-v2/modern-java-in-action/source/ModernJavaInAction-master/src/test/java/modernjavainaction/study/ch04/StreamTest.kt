package modernjavainaction.study.ch04

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.stream.Collector
import java.util.stream.Collectors

class StreamTest {
    @Test
    fun 스트림_기본() {
        val result = menu.filter { it.calories < 400 }
            .sortedBy { it.calories }
            .toList()

        assertThat(result.size).isEqualTo(3)
        assertThat(result).allMatch { it.calories < 400 }
    }

    @Test
    fun 파이프라인() {
        val result = menu.filter { it.calories > 300 }
            .map { it.name }
            .take(3)
            .toList()

        assertThat(result.size).isEqualTo(3)
        assertThat(result).containsAll(listOf("pork", "beef", "chicken"))
    }

    @Test
    fun 퀴즈_4_1() {
        val highCaloricDishes = menu.filter { it.calories > 300 }
            .toList()

        assertThat(highCaloricDishes).allMatch { it.calories > 300 }
    }

    @Test
    fun 중간연산() {
        val names = menu.stream()
            .filter {
                println("filtering ${it.name}")
                it.calories > 300
            }
            .map {
                println("mapping ${it.name}")
                it.name
            }
            .limit(3)
            .collect(Collectors.toList())

        assertThat(names).containsAll(listOf("pork", "beef", "chicken"))
    }
}