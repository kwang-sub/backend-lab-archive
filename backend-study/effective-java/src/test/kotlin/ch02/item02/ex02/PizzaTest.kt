package ch02.item02.ex02

import ch02.item02.ex02.Calzone
import ch02.item02.ex02.NyPizza
import ch02.item02.ex02.Size
import ch02.item02.ex02.Topping
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class PizzaTest {

    @Test
    fun 뉴욕피자_생성_테스트() {
        // given
        val size = Size.LARGE
        // when
        val result = NyPizza.Builder(size)
            .addTopping(Topping.HAM)
            .addTopping(Topping.MUSHROOM)
            .build() as NyPizza
        //then
        assertThat(result.size).isEqualTo(size)
        assertThat(result.toppings.size).isEqualTo(2)
        assertThat(result.toppings).contains(Topping.HAM, Topping.MUSHROOM)
    }

    @Test
    fun 칼조네_생성_테스트() {
        // given
        val sauceInSide = false
        // when
        val result = Calzone.Builder(sauceInSide)
            .addTopping(Topping.ONION)
            .build() as Calzone
        //then
        assertThat(result.sauceInSide).isEqualTo(sauceInSide)
        assertThat(result.toppings.size).isEqualTo(1)
        assertThat(result.toppings).contains(Topping.ONION)
    }
}