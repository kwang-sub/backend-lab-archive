package org.test.service.dto

class ErrorDto(
    val code: String,
    val message: String,
    val errors: Map<String, List<Any>>
) {

}