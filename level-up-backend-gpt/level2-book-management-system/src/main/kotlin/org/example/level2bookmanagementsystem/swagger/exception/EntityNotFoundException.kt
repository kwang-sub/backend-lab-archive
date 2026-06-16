package org.example.level2bookmanagementsystem.swagger.exception

import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import kotlin.reflect.KClass


class EntityNotFoundException(
    clazz: KClass<*>,
) : CustomBadRequestException(ErrorCode.NOT_FOUND_ENTITY, ErrorCode.NOT_FOUND_ENTITY.getMessage(clazz.simpleName.toString())) {
}