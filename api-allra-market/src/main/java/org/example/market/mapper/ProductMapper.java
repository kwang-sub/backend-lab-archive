package org.example.market.mapper;

import org.example.market.dto.response.ProductResDto;
import org.example.market.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductResDto> {
}
