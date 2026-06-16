package modernjavainaction.study.ch02

import modernjavainaction.base.chap02.FilteringApples
import modernjavainaction.base.chap02.FilteringApples.Apple
import modernjavainaction.base.chap02.FilteringApples.Color


fun MutableList<Apple>.filterGreenApples(): MutableList<Apple> {
    val result: MutableList<Apple> = mutableListOf()

    for (apple: Apple in this) {
        if (FilteringApples.Color.GREEN == apple.color) {
            result.add(apple)
        }
    }
    return result
}

fun MutableList<Apple>.filterApplesByColor(color: Color): MutableList<Apple> {
    val result: MutableList<Apple> = mutableListOf()

    for (apple: Apple in this) {
        if (color == apple.color) {
            result.add(apple)
        }
    }
    return result
}

fun MutableList<Apple>.filterApplesByWeight(weight: Int): MutableList<Apple> {
    val result: MutableList<Apple> = mutableListOf()

    for (apple: Apple in this) {
        if (apple.weight > weight) {
            result.add(apple)
        }
    }
    return result
}

fun MutableList<Apple>.filterApples(color: Color, weight: Int, flag: Boolean): MutableList<Apple> {
    val result: MutableList<Apple> = mutableListOf()

    for (apple: Apple in this) {
        if (apple.weight > weight) {
            result.add(apple)
        }
    }
    return result
}

fun MutableList<Apple>.filterApples(p: ApplePredicate): MutableList<Apple> {
    val result: MutableList<Apple> = mutableListOf()

    for (apple: Apple in this) {
        if (p.test(apple)) {
            result.add(apple)
        }
    }
    return result
}

