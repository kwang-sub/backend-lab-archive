package modernjavainaction.study.ch02

import modernjavainaction.base.chap02.FilteringApples.Apple

fun MutableList<Apple>.prettyPrintApple(f: (Apple) -> String) {
    for (apple in this) {
        println(f(apple))
    }
}

fun applePrintFormat(apple: Apple) = "${apple.color} ${apple.weight}"