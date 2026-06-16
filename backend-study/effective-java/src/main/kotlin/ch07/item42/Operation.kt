package ch07.item42

import java.util.function.DoubleBinaryOperator

enum class Operation(
    val symbol: String,
    val op: DoubleBinaryOperator,
) {
    PLUS("+", { x, y -> x + y }),
    MINUS("-", { x, y -> x - y }),
    TIMES("*", { x, y -> x * y }),
    DIVIDE("/", { x, y -> x / y });

    override fun toString(): String {
        return symbol
    }

    fun apply(x: Double, y: Double): Double {
        return op.applyAsDouble(x, y)
    }
}