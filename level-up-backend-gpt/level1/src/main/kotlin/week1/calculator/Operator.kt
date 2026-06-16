package week1.calculator

enum class Operator(
    val value: String
) {
    ADD("+"){

        override fun operation(num1: Int, num2: Int): Int {
            return num1 + num2
        }
    },
    SUB("-"){
        override fun operation(num1: Int, num2: Int): Int {
            return num1 - num2
        }
    },
    MUL("*"){
        override fun operation(num1: Int, num2: Int): Int {
            return num1 * num2
        }
    },
    DIV("/"){
        override fun operation(num1: Int, num2: Int): Int {
            if (num2 == 0) throw IllegalArgumentException("0으로 나눌 수 없습니다.")
            return num1 / num2
        }
    };


    companion object {
        fun from(value: String): Operator {
            return entries.firstOrNull { it.symbol == value }
                ?: throw IllegalArgumentException("지원하지 않는 연산자입니다.")
        }
    }

    private val symbol: String = value

    abstract fun operation(num1: Int, num2: Int): Int
}