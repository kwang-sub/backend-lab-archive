package modernjavainaction.study.ch03

import modernjavainaction.base.chap03.Apple
import java.util.function.Function
import java.util.function.Supplier

fun main() {
    val c1 = Supplier(::Apple)
    val apple = c1.get()
    val c2 = Function<Int, Apple>(::Apple)
    val apple2 = c2.apply(10)

    val weights = listOf(1, 2, 3, 4)
    val apples = weights.map(::Apple)
    println(apples)

    val sort = apples.sortedWith(Comparator.comparing(Apple::getWeight).reversed())
    println(sort)
    val sort2 = apples.sortedWith { a: Apple, b: Apple -> b.weight.compareTo(a.weight) }
    println(sort2)
}