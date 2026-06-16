package com.example.sample

class Calculator {
    fun calculate(num1: Long, operator: String, num2: Long): Long =
        when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> throw InvalidOperatorException()
        }
}