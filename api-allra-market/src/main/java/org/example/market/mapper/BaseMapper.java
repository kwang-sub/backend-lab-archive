package org.example.market.mapper;

public interface BaseMapper<E, D> {
    D toDto(E entity);
}
