package org.example.level2bookmanagementsystem.base.mapper

import org.mapstruct.MapperConfig

// TODO 블로그 작성 Mapstruct 사용법
@MapperConfig
interface BaseMapper<C, E, R, SR> {
    fun toEntity(command: C): E
    fun toResponse(entity: E): R
    fun toSimpleResponse(entity: E): SR
    fun toEntityList(commands: List<C>): List<E>
    fun toResponseList(entities: List<E>): List<R>
    fun toSimpleResponseList(entities: List<E>): List<SR>
}