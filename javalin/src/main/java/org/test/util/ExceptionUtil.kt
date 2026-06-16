package org.test.util

import io.javalin.validation.ValidationError
import io.javalin.validation.ValidationException

fun createValidationException(
    field: String,
    message: String? = null,
    value: String? = null,
    args: Map<String, Any?> = mapOf(),
): ValidationException {
    return ValidationException(mapOf(field to listOf(ValidationError(message = message ?: "", value = value, args = args))))

}