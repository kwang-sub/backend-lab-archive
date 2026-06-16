package org.example.market.dto.request;

import java.math.BigDecimal;

public record PaymentReqDto(
        Long orderId,
        BigDecimal amount
) {
}
