package org.example.market.mapper;

import org.example.market.dto.response.OrderResDto;
import org.example.market.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderResDto> {
}
