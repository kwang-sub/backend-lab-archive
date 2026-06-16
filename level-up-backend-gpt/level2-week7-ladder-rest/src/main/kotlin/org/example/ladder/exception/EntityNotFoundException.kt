package org.example.ladder.exception

import org.example.ladder.constant.ServiceErrorCode
import org.example.ladder.exception.common.CustomBadRequestException

class EntityNotFoundException(
    className: Class<*>,
) : CustomBadRequestException(
    ServiceErrorCode.ENTITY_NOT_FOUND.code,
    String.format(ServiceErrorCode.ENTITY_NOT_FOUND.message, className.simpleName)
)