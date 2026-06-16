package org.example.level2bookmanagementsystem.swagger.exception

import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode

open class CustomBadRequestException(
    val errorCode: ErrorCode,
    override val message: String
) : RuntimeException(message) {
    constructor(errorCode: ErrorCode) : this(errorCode, errorCode.message)
}