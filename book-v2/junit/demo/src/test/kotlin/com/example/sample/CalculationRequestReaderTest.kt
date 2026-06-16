package com.example.sample

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class CalculationRequestReaderTest{

    @Test
    fun System_in으로_데이터를_읽어들일_수_있다() {
        // given
        val calculationRequestReader = CalculationRequestReader()

        // when
        System.setIn(ByteArrayInputStream("2 + 3".toByteArray()))
        val result = calculationRequestReader.read()

        //then
        assertEquals(2, result.num1)
        assertEquals("+", result.operator)
        assertEquals(3, result.num2)
    }
}