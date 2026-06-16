package org.example.market.mapper;

import org.example.market.dto.response.CartResDto;
import org.example.market.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CartMapper extends BaseMapper<Cart, CartResDto> {
}
