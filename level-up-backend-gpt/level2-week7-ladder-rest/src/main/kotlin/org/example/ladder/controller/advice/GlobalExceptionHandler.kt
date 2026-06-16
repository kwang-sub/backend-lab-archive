package org.example.ladder.controller.advice

import jakarta.servlet.http.HttpServletRequest
import org.example.ladder.constant.ServiceErrorCode
import org.example.ladder.dto.response.ErrorDetailResponse
import org.example.ladder.dto.response.ErrorResponse
import org.example.ladder.exception.common.CustomBadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.ZonedDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val fieldErrors = ex.bindingResult.fieldErrors.map {
            ErrorDetailResponse(
                field = it.field,
                message = it.defaultMessage ?: "Invalid value"
            )
        }

        val errorResponse = ErrorResponse(
            code = ServiceErrorCode.INVALID_VALUE.code,
            message = ServiceErrorCode.INVALID_VALUE.message,
            status = HttpStatus.BAD_REQUEST.value(),
            path = request.requestURI,
            timestamp = ZonedDateTime.now(),
            fieldErrors = fieldErrors
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }


    @ExceptionHandler(value = [Exception::class])
    fun handleExceptions(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        var status: HttpStatus
        var code: String
        when (ex) {
            is CustomBadRequestException -> {
                status = HttpStatus.BAD_REQUEST
                code = ex.code
            }
            else -> {
                ex.printStackTrace()
                status = HttpStatus.INTERNAL_SERVER_ERROR
                code = "E00000"
            }
        }

        val errorResponse = ErrorResponse(
            code = code,
            message = ex.message ?: "An unexpected error occurred",
            status = status.value(),
            path = request.requestURI,
            timestamp = ZonedDateTime.now(),
            fieldErrors = emptyList()
        )
        return ResponseEntity.status(status).body(errorResponse)
    }
}