package org.example.market.dto.response;

import org.example.market.enums.OrderStatus;
import org.example.market.entity.Amount;

import java.time.LocalDateTime;

public record OrderResDto(
        Long id,
        OrderStatus orderStatus,
        Amount amount,
        LocalDateTime orderAt
) {
}
