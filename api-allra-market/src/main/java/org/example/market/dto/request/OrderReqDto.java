package org.example.market.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public record OrderReqDto(
        @NotNull
        BigDecimal totalAmount,
        @Valid
        Set<OrderCartReqDto> orderCarts
) {
}
