package org.example.level2bookmanagementsystem.swagger.controller.advice

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import jakarta.servlet.http.HttpServletRequest
import org.example.level2bookmanagementsystem.base.dto.response.ErrorDetailResponse
import org.example.level2bookmanagementsystem.base.dto.response.ErrorResponse
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.example.level2bookmanagementsystem.swagger.exception.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
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
            code = ErrorCode.INVALID_VALUE.code,
            message = ErrorCode.INVALID_VALUE.message,
            status = HttpStatus.BAD_REQUEST.value(),
            path = request.requestURI,
            timestamp = ZonedDateTime.now(),
            fieldErrors = fieldErrors
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        // Jackson이 Enum 역직렬화 실패 등으로 던지는 예외를 받아 BAD_REQUEST로 변환
        // ex에서 실패한 필드 명 추출하는 방법
        ex
        val fieldErrors = mutableListOf<ErrorDetailResponse>()

        val cause = ex.cause
        if (cause is InvalidFormatException) {
            val fieldName = cause.path.firstOrNull()?.fieldName
            val allowed = cause.targetType?.let { t ->
                if (t.isEnum) t.enumConstants.joinToString(", ") else null
            }
            val message = buildString {
                append("Invalid value: ${cause.value}")
                if (!allowed.isNullOrEmpty()) append(" (allowed: $allowed)")
            }
            if (fieldName != null) {
                fieldErrors.add(ErrorDetailResponse(field = fieldName, message = message))
            }
        } else if (cause is JsonMappingException) {
            val fieldName = cause.path.firstOrNull()?.fieldName
            val message = cause.localizedMessage ?: "Invalid request body"
            if (fieldName != null) {
                fieldErrors.add(ErrorDetailResponse(field = fieldName, message = message))
            }
        }

        val errorResponse = ErrorResponse(
            code = ErrorCode.INVALID_VALUE.code,
            message = ErrorCode.INVALID_VALUE.message,
            status = HttpStatus.BAD_REQUEST.value(),
            path = request.requestURI,
            timestamp = ZonedDateTime.now(),
            fieldErrors = fieldErrors
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }


    @ExceptionHandler(value = [CustomBadRequestException::class])
    fun handleExceptions(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        var status: HttpStatus
        var code: String
        var message: String
        when (ex) {
            is EntityNotFoundException -> {
                status = HttpStatus.NOT_FOUND
                code = ex.errorCode.code
                message = ex.message
            }

            is CustomBadRequestException -> {
                status = ex.errorCode.httpCode
                code = ex.errorCode.code
                message = ex.errorCode.message
            }

            else -> {
                val cause = ex.cause
                if (cause == null) {
                    ex.printStackTrace()
                    status = HttpStatus.INTERNAL_SERVER_ERROR
                    code = ErrorCode.INTERNAL_SERVER_ERROR.code
                    message = ErrorCode.INTERNAL_SERVER_ERROR.message
                } else if (cause is EntityNotFoundException) {
                    status = HttpStatus.NOT_FOUND
                    code = cause.errorCode.code
                    message = cause.errorCode.message
                } else if (cause is CustomBadRequestException) {
                    status = cause.errorCode.httpCode
                    code = cause.errorCode.code
                    message = cause.errorCode.message
                } else {
                    ex.printStackTrace()
                    status = HttpStatus.INTERNAL_SERVER_ERROR
                    code = ErrorCode.INTERNAL_SERVER_ERROR.code
                    message = ErrorCode.INTERNAL_SERVER_ERROR.message
                }
            }
        }

        val errorResponse = ErrorResponse(
            code = code,
            message = message,
            status = status.value(),
            path = request.requestURI,
            timestamp = ZonedDateTime.now(),
            fieldErrors = emptyList()
        )
        return ResponseEntity.status(status).body(errorResponse)
    }
}