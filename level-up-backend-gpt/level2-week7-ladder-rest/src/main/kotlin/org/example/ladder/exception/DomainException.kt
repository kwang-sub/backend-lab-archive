package org.example.ladder.exception

import org.example.ladder.constant.DomainErrorCode
import org.example.ladder.exception.common.CustomBadRequestException

class DomainException(errorCode: DomainErrorCode) :
    CustomBadRequestException(errorCode.code, errorCode.message) {
}