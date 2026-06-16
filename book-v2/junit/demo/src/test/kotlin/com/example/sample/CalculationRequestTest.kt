package com.example.sample

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalculationRequestTest {

    @Test
    fun 유효한_숫자를_파싱할_수_있다() {
        //given
        val parts = listOf("1", "+", "2")
        //when
        val calculationRequest = CalculationRequest(parts)
        //then
        assertEquals(1, calculationRequest.num1)
        assertEquals("+", calculationRequest.operator)
        assertEquals(2, calculationRequest.num2)
    }

    @Test
    fun 세자리_숫자가_넘어가는_유효한_숫자를_파싱할_수_있다() {
        //given
        val parts = listOf("123", "+", "2222")
        //when
        val calculationRequest = CalculationRequest(parts)
        //then
        assertEquals(123, calculationRequest.num1)
        assertEquals("+", calculationRequest.operator)
        assertEquals(2222, calculationRequest.num2)
    }

    @Test
    fun 문자열_길이_예외_확인() {
        //given
        val parts = listOf("123", "++", "2222")
        //when
        //then
        assertThrows(InvalidOperatorException::class.java) { CalculationRequest(parts) }
    }

    @Test
    fun 틀린_연산자_예외_확인() {
        //given
        val parts = listOf("123", "x", "2222")
        //when
        //then
        assertThrows(InvalidOperatorException::class.java) { CalculationRequest(parts) }
    }

    @Test
    fun 입력_값_크기_예외_확인() {
        //given
        val parts = listOf("123", "x", "2222", "??")
        //when
        //then
        assertThrows(BadRequestException::class.java) { CalculationRequest(parts) }
    }
}