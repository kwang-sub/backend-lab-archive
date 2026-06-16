package ch02.item02.ex01

import ch02.item02.ex01.NutritionFacts
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class NutritionFactsTest {
    @Test
    fun 빌더로_객체_생성() {
        // given
        val calories: Int = 1
        // when
        val result = NutritionFacts.Builder(1, 2)
            .calories(1)
            .build()
        //then
        assertThat(result.calories).isEqualTo(calories)
    }
}