package org.example.ladder.exception.common

open class CustomBadRequestException(val code: String, message: String) : RuntimeException(message) {
}