package ch05.item30

import java.util.Objects
import java.util.function.UnaryOperator

val IDENTITY_FN: UnaryOperator<Any> = UnaryOperator { it }

@SuppressWarnings("unchecked")
fun <T> identityFunction(): UnaryOperator<T> {
    return IDENTITY_FN as UnaryOperator<T>
}

fun <E : Comparable<E>> max(c: MutableCollection<E>): E {
    if (c.isEmpty())
        throw IllegalArgumentException()

    var result = c.first()
    for (e in c) {
        if (e > result) {
            result = Objects.requireNonNull(e)
        }
    }
    return result
}

fun main() {
    val strings = arrayOf("삼베", "대마", "나일론")
    val sameString: UnaryOperator<String> = identityFunction()
    for (s: String in strings) {
        println(sameString.apply(s))
    }

    val numbers = arrayOf(1, 2L, 4.0)
    val sameNumber: UnaryOperator<Number> = identityFunction()
    for (n in numbers) {
        println(sameNumber.apply(n))
    }
}