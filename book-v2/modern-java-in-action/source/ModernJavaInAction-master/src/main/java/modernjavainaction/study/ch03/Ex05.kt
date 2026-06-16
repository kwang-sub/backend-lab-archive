package modernjavainaction.study.ch03

import modernjavainaction.base.chap02.FilteringApples.Apple
import modernjavainaction.base.chap02.FilteringApples.Color
import java.util.function.Function
import java.util.function.Predicate


fun main() {
    val redApple: Predicate<Apple> = Predicate { it.color == Color.RED }
    val notRedApple = redApple.negate()
    val redAndHeavyApple = redApple.and { it.weight > 150 }

    val f = Function<Int, Int> { it + 1 }
    val g = Function<Int, Int> { it * 2 }
    val h = f.andThen(g)
    val i = f.compose(g)
    println(h.apply(1))
    println(i.apply(1))
}