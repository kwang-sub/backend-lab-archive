package org.test.util.handler

import com.auth0.jwt.exceptions.TokenExpiredException
import io.javalin.http.Context
import io.javalin.http.ExceptionHandler
import io.javalin.http.HttpStatus
import io.javalin.validation.ValidationException

val validationExceptionHandler =
    ExceptionHandler { e: ValidationException, ctx: Context ->
        ctx.json(e.errors).status(HttpStatus.BAD_REQUEST)
    }

val tokenExpiredExceptionHandler =
    ExceptionHandler { e: TokenExpiredException, ctx: Context ->
        ctx.json(
            e.message!!
        ).status(HttpStatus.BAD_REQUEST)
    }