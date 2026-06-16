package week1.calculator

class Calculator(
    private val calculatorStringParser: CalculatorStringParser,
) {


    fun calculate(input: String?): Int {
        if (input.isNullOrBlank()) return 0

        val inputSplitList = calculatorStringParser.parse(input)

        val numArr = mutableListOf<Int>()
        val operatorList = mutableListOf<Operator>()

        for (p in inputSplitList.withIndex()) {
            if (p.index % 2 != 0) {
                val operator = Operator.from(p.value)
                operatorList.add(operator)
            } else {
                val n = p.value.toIntOrNull() ?: throw IllegalArgumentException("숫자가 아닙니다.")
                if (n < 0) throw IllegalArgumentException("음수는 지원하지 않습니다.")
                numArr.add(n)
            }
        }

        val result = numArr.reduceIndexed { index, acc, num ->
            if (index == 0) acc
            else operatorList[index - 1].operation(acc, num)
        }

        return result
    }
}