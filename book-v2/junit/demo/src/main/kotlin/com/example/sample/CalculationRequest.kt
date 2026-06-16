package com.example.sample

class CalculationRequest(
    parts: List<String>,
) {
    init {
        if (parts.size != 3) throw BadRequestException()
        if (parts[1].length != 1 || isInvalidOperator(parts[1]))
            throw InvalidOperatorException()
    }

    val num1 = parts[0].toLong()
    val operator = parts[1]
    val num2 = parts[2].toLong()

    private fun isInvalidOperator(operator: String): Boolean {
        val operatorList = listOf("+", "-", "/", "*")
        return !operatorList.contains(operator)
    }
}