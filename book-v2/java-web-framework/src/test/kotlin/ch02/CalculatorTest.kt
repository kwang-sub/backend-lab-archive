package ch02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @BeforeEach
    fun init() {
        calculator = Calculator()
        println("init")
    }

    @AfterEach
    fun teardown() {
        println("teardown")
    }

    @Test
    fun add() {
        val result = calculator.add(10, 5)
        assertThat(result).isEqualTo(15)
        println("add")
    }

    @Test
    fun subtract() {
        val result = calculator.subtract(10, 5)
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun multiply() {
        val result = calculator.multiply(10, 5)
        assertThat(result).isEqualTo(50)
    }

    @Test
    fun divide() {
        val result = calculator.divide(10, 5)
        assertThat(result).isEqualTo(2)
    }

}