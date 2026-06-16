package ch06.item38

import kotlin.math.pow

enum class ExtendedOperation(
    private val symbol: String,
) : Operation {
    EXP("^") {
        override fun apply(x: Double, y: Double): Double {
            return x.pow(y)
        }
    },
    REMAINDER("%") {
        override fun apply(x: Double, y: Double): Double {
            return x % y
        }
    };

    override fun toString(): String {
        return symbol
    }
}