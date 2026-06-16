package org.example.market.mapper;

import org.example.market.dto.response.CustomerResDto;
import org.example.market.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<Customer, CustomerResDto> {
}
