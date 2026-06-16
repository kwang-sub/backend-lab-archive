package org.example.market.dto.response;

import org.example.market.enums.TransactionStatus;

public record PaymentResDto(
        TransactionStatus status,
        String transactionId,
        String message
) {
}
