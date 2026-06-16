package modernjavainaction.study.ch15

import modernjavainaction.base.chap15.Functions
import java.util.function.IntConsumer

fun main() {
    val x = 1337

    val result = Result()

    f(x) {
        result.left = it
        println("f ${result.left}  ${result.right}")
    }
    g(x) {
        result.right = it
        println("g ${result.left}  ${result.right}")
    }
}

fun f(x: Int, dealWithResult: IntConsumer) {
    dealWithResult.accept(x)
}

fun g(x: Int, dealWithResult: IntConsumer) {
    dealWithResult.accept(x)
}

