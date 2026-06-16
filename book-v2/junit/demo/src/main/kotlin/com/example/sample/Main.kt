package com.example.sample

fun main(args: Array<String>) {
    val calculationRequestReader = CalculationRequestReader()
    val calculationRequest  = calculationRequestReader.read()

    val answer = Calculator().calculate(
        calculationRequest.num1,
        calculationRequest.operator,
        calculationRequest.num2
    )

    println(answer)
}