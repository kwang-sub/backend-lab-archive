package org.example.ladder.util

import org.example.ladder.constant.DomainErrorCode
import org.example.ladder.exception.DomainException

fun requireDomain(condition: Boolean, errorCode: DomainErrorCode) {
    if (!condition) {
        throw DomainException(errorCode)
    }
}