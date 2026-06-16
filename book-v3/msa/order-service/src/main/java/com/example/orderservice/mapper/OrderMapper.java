package com.example.orderservice.mapper;

import com.example.orderservice.entity.Order;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends BaseMapper<RequestOrder, ResponseOrder, Order> {

    @Override
    Order toEntity(RequestOrder command);

    @Override
    ResponseOrder toDto(Order command);
}
