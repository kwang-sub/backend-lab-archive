package com.example.sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun 덧셈_연산을_할_수_있다() {
        // given
        val num1: Long = 2
        val operator = "+"
        val num2: Long = 3

        val calculator = Calculator()
        val result = calculator.calculate(num1, operator, num2)
        assertEquals(5, result)
    }

    @Test
    fun 뺄셈_연산을_할_수_있다() {
        // given
        val num1: Long = 2
        val operator = "-"
        val num2: Long = 3

        val calculator = Calculator()
        val result = calculator.calculate(num1, operator, num2)
        assertEquals(-1, result)
    }

    @Test
    fun 나눗셈_연산을_할_수_있다() {
        // given
        val num1: Long = 4
        val operator = "/"
        val num2: Long = 2

        val calculator = Calculator()
        val result = calculator.calculate(num1, operator, num2)
        assertEquals(2, result)
    }

    @Test
    fun 곱셈_연산을_할_수_있다() {
        // given
        val num1: Long = 2
        val operator = "*"
        val num2: Long = 3

        val calculator = Calculator()
        val result = calculator.calculate(num1, operator, num2)
        assertEquals(6, result)
    }

    @Test
    fun 잘못된_연산자가_요청으로_들어올_경우_에러가_난다() {
        // given
        val num1: Long = 2
        val operator = "x"
        val num2: Long = 3

        val calculator = Calculator()
        assertThrows(InvalidOperatorException::class.java) { calculator.calculate(num1, operator, num2) }
    }
}