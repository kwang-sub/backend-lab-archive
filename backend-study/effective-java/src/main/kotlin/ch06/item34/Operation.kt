package ch06.item34

import java.util.Optional

enum class Operation(
    val symbol: String,
) {
    PLUS("+") {
        override fun apply(x: Double, y: Double): Double {
            return x + y
        }
    },
    MINUS("-") {
        override fun apply(x: Double, y: Double): Double {
            return x - y
        }
    },
    TIMES("*") {
        override fun apply(x: Double, y: Double): Double {
            return x * y
        }
    },
    DIVIDE("/") {
        override fun apply(x: Double, y: Double): Double {
            return x / y
        }
    };

    abstract fun apply(x: Double, y: Double): Double

    override fun toString(): String {
        return symbol
    }

    companion object {
        private val stringToEnum: Map<String, Operation> =
            entries.associateBy { it.symbol }

        fun fromString(symbol: String): Optional<Operation> {
            return Optional.ofNullable(stringToEnum[symbol])
        }
    }
}

