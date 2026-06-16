package modernjavainaction.study.ch06

import modernjavainaction.base.chap04.Dish
import modernjavainaction.base.chap06.GroupingTransactions
import modernjavainaction.study.ch04.menu
import org.junit.jupiter.api.Test
import java.util.stream.Collectors
import java.util.stream.IntStream

class Test01 {

    @Test
    fun 그룹화() {
        GroupingTransactions.transactions.groupBy { it.currency }
//            .forEach { println(it) }

        menu.stream().collect(Collectors.counting())
            .let { println(it) }

        menu.sumOf { it.calories }

        menu.joinToString(prefix = " k ") { it.name }

        menu.stream()
            .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toList())))
//            .forEach { println(it) }

        menu.stream()
            .collect(
                Collectors.groupingBy(
                    Dish::getType,
                    Collectors.filtering({ it.calories > 500 }, Collectors.toList())
                )
            )
            .forEach { println(it) }
    }

    @Test
    fun 맵() {
        val dishTags = modernjavainaction.base.chap06.Dish.dishTags
        menu.groupBy { it.type }
            .map { type -> type to type.value.map { it.name } }
//            .forEach { println(it) }

        menu.groupBy { it.type }
            .map {
                it.value.groupBy {
                    if (it.calories <= 400)
                        return@groupBy "DIEF"
                    else if (it.calories <= 700)
                        return@groupBy "NORMAL"
                    else return@groupBy "FAT"
                }
            }
//            .forEach { println(it) }

        menu.groupBy { it.type }
            .map { it.key to it.value.sumOf { it.calories } }
            .forEach { println(it) }

    }

    @Test
    fun 분할() {
        menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy { it.type }))
            .forEach(System.out::println)
        menu.partition { it.isVegetarian }
            .let {
                it.first.groupBy { it.type }.let { println(it) }
                it.second.groupBy { it.type }.let { println(it) }

                it.first.maxBy { it.calories }.let { println(it) }
            }

        IntRange(2, 10).partition { isPrime(it) }
            .let { println(it) }

        IntRange(2, 10).toList().stream().collect(PrimeNumbersCollector())
            .let { println(it) }

    }


    fun isPrime(candidate: Int): Boolean {
        return IntStream.range(2, candidate)
            .noneMatch { candidate % it == 0 }
    }
}