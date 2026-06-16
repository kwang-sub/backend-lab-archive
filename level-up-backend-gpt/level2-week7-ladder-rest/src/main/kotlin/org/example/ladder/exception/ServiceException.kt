package org.example.ladder.exception

import org.example.ladder.constant.ServiceErrorCode
import org.example.ladder.exception.common.CustomBadRequestException

class ServiceException(errorCode: ServiceErrorCode): CustomBadRequestException(errorCode.code, errorCode.message) {
}