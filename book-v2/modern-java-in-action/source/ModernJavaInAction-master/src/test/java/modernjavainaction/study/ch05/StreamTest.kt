package modernjavainaction.study.ch05

import modernjavainaction.base.chap04.Dish
import modernjavainaction.base.chap05.Trader
import modernjavainaction.base.chap05.Transaction
import modernjavainaction.study.ch04.menu
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StreamTest {
    var raoul = Trader("Raoul", "Cambridge")
    var mario = Trader("Mario", "Milan")
    var alan = Trader("Alan", "Cambridge")
    var brian = Trader("Brian", "Cambridge")
    val transactions = listOf(
        Transaction(brian, 2011, 300),
        Transaction(raoul, 2012, 1000),
        Transaction(raoul, 2011, 400),
        Transaction(mario, 2012, 710),
        Transaction(mario, 2012, 700),
        Transaction(alan, 2012, 950)
    )


    @Test
    fun 중복제거() {
        val list = listOf(1, 2, 1, 3, 3, 2, 4)
        val result = list.filter { it % 2 == 0 }
            .distinct()

        assertThat(result).allMatch { it % 2 == 0 }
        assertThat(result.size).isEqualTo(result.toSet().size)
    }

    @Test
    fun takeWhile() {
        val filteredMenu = specialMenu.filter { it.calories < 320 }
        val slicedMenu1 = specialMenu.takeWhile { it.calories < 320 }

        assertThat(filteredMenu).isNotEqualTo(slicedMenu1)
    }

    @Test
    fun dropWhile() {
        val dropWhile = specialMenu.dropWhile { it.calories < 320 }
        assertThat(dropWhile).anyMatch { it.calories < 320 }
    }

    @Test
    fun limit() {
        val take = specialMenu.take(3)
        assertThat(take.size).isEqualTo(3)
    }

    @Test
    fun 퀴즈_5_1() {
        val meat = menu.filter { it.type == Dish.Type.MEAT }
            .take(2)

        assertThat(meat.size).isEqualTo(2)
        assertThat(meat).allMatch { it.type == Dish.Type.MEAT }
    }

    @Test
    fun map() {
        val nameList = menu.map(Dish::getName)
        assertThat(nameList).allMatch { it.javaClass == String::class.java }

        val charList = nameList.flatMap { it.split("").filter(String::isNotBlank) }
            .also { println(it) }
    }

    @Test
    fun 퀴즈_5_2() {
        val numList = listOf(1, 2, 3, 4, 5)
        val result = numList.map { it * it }
        assertThat(result).isEqualTo(listOf(1, 4, 9, 16, 25))

        val numList1 = listOf(1, 2, 3)
        val numList2 = listOf(3, 4)

        val result2 = numList1.flatMap { num1 ->
            numList2.map { num2 -> num1 to num2 }
        }
        assertThat(result2).isEqualTo(
            listOf(
                1 to 3,
                1 to 4,
                2 to 3,
                2 to 4,
                3 to 3,
                3 to 4,
            )
        )

        val result3 = result2.filter { (it.first + it.second) % 3 == 0 }
        assertThat(result3).isEqualTo(listOf(2 to 4, 3 to 3))
    }

    @Test
    fun anyMatch() {
        val result = menu.any { it.isVegetarian }
        assertThat(result).isTrue()
    }

    @Test
    fun allMatch() {
        val result = menu.all { it.calories < 1000 }
        assertThat(result).isTrue()
    }

    @Test
    fun noneMatch() {
        val result = menu.none { it.calories >= 1000 }
        assertThat(result).isTrue()
    }

    @Test
    fun findAny() {
        val result = menu.find { it.isVegetarian }
        assertThat(result != null).isTrue()

        val someNumbers = listOf(1, 2, 3, 4, 5)
        val i = someNumbers.map { it * it }
            .first { it % 3 == 0 }
        println(i)
    }

    @Test
    fun reduce() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val sum = numbers.stream().reduce(0) { a: Int, b: Int -> a + b }
        val sum2 = numbers.stream().reduce(0, Integer::sum)
        val sum3 = numbers.stream().reduce(Integer::sum)

        assertThat(sum).isEqualTo(15)
        assertThat(sum2).isEqualTo(15)
        assertThat(sum3.isPresent).isTrue()
    }

    @Test
    fun 퀴즈_5_3() {
        val count = menu.map { 1 }.reduce(Integer::sum)
        assertThat(count).isEqualTo(menu.size)
    }

    @Test
    fun 퀴즈_2011년_트랜잭션_값_오름차순() {
        val result = transactions.filter { it.year == 2011 }
            .map { it.value }
            .sortedBy { it }
            .toList()

        assertThat(result).isEqualTo(listOf(300, 400))
        assertThat(result[0]).isEqualTo(result.min())
    }

    @Test
    fun 퀴즈_도시_중복제거() {
        val result = transactions.map { it.trader.city }
            .distinct()

        assertThat(result).isEqualTo(listOf("Cambridge", "Milan"))
    }

    @Test
    fun 케임브리지_근무자_이름순_정렬() {
        val result = transactions.map { it.trader }
            .filter { it.city == "Cambridge" }
            .sortedBy { it.name }

        assertThat(result).allMatch { it.city == "Cambridge" }
        assertThat(result.first().name).isEqualTo("Alan")
    }

    @Test
    fun 거래자_이름_정령() {
        val result = transactions.map { it.trader.name }
            .distinct()
            .sortedBy { it }
        assertThat(result).isEqualTo(listOf("Alan", "Brian", "Mario", "Raoul"))
        assertThat(result.first()).isEqualTo("Alan")
    }

    @Test
    fun 밀라노_거래자_유무() {
        val result = transactions.any { it.trader.city == "Milan" }
        assertThat(result).isTrue()
    }

    @Test
    fun 케임브리지_거래자_트랜잭션값_출력() {
        transactions.filter { it.trader.city == "Cambridge" }.forEach { println(it.value) }
    }

    @Test
    fun 트랜잭션_최댓값() {
        val result = transactions.maxOfOrNull { it.value }

        assertThat(result).isEqualTo(1000)
    }

    @Test
    fun 트랜잭션_최솟값() {
        val result = transactions.minOfOrNull { it.value }

        assertThat(result).isEqualTo(300)
    }

}