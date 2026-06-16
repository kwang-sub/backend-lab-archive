package modernjavainaction.study.ch02

import modernjavainaction.base.chap02.FilteringApples
import modernjavainaction.base.chap02.FilteringApples.Apple
import modernjavainaction.base.chap02.FilteringApples.Color
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class AppleFilterKtTest {

    var appleList: MutableList<Apple> = mutableListOf(
        Apple(150, FilteringApples.Color.GREEN),
        Apple(250, FilteringApples.Color.GREEN),
        Apple(150, FilteringApples.Color.RED),
        Apple(250, FilteringApples.Color.RED),
    )

    @Test
    fun 녹색사과_필터링_테스트() {
        // given

        // when
        val result = appleList.filterGreenApples().map { it.color }
        //then
        assertThat(result).allMatch { it == Color.GREEN }
    }

    @Test
    fun 원하는색_필터링() {
        // given

        // when
        val result = appleList.filterApplesByColor(Color.RED)
        //then
        assertThat(result).allMatch { it.color == Color.RED }
    }

    @Test
    fun 무게이상_필터링() {
        // given

        // when
        val result = appleList.filterApplesByWeight(151)
        //then
        assertThat(result).isNotEmpty
        assertThat(result).allMatch { it.weight > 151 }
    }

    @Test
    fun 인터페이스_필터링() {
        // given

        // when
        val result = appleList.filterApples(AppleHeavyWeightPredicate())
        //then
        assertThat(result).isNotEmpty
        assertThat(result).allMatch { it.weight > 151 }
    }

    @Test
    fun 출력_테스트() {
        // given

        // when
        appleList.prettyPrintApple(::applePrintFormat)
        //then
    }
}