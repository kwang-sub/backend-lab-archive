package ch06.item38

enum class BasicOperation(
    private val symbol: String,
) : Operation {
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

    override fun toString(): String {
        return symbol
    }
}

fun main() {
    val x = 1.0
    val y = 3.0
    extracted(BasicOperation::class.java, x, y)
    extracted(ExtendedOperation::class.java, x, y)
}

private fun <T> extracted(clazz: Class<T>, x: Double, y: Double) where T: Enum<T>, T : Operation {
    for (op: Operation in clazz.enumConstants) {
        println("$x $op $y  ${op.apply(x, y)}")
    }
}