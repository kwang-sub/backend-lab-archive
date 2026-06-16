package org.test.service.mapper

import org.mapstruct.BeanMapping
import org.mapstruct.MappingTarget
import org.mapstruct.Named
import org.mapstruct.NullValuePropertyMappingStrategy

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param D DTO type parameter.
 * @param E Entity type parameter.
 */

interface EntityMapper<D, C, E> {

    fun toEntity(command: C): E

    fun toDto(entity: E): D

    fun toEntity(command: MutableList<C>): MutableList<E>

    fun toDto(entityList: MutableList<E>): MutableList<D>

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun partialUpdate(@MappingTarget entity: E, dto: D)
}
