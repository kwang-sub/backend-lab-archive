package org.example.market.enums;

import org.example.market.exception.InvalidOrderStatusException;

import java.util.Set;

public enum OrderStatus {

    PAYMENT_FAILED(Set.of()),
    PAYMENT_COMPLETE(Set.of()),
    AWAITING_PAYMENT(Set.of(OrderStatus.PAYMENT_COMPLETE, OrderStatus.PAYMENT_FAILED));

    private final Set<OrderStatus> allowedTransitions;

    OrderStatus(Set<OrderStatus> allowedTransitions) {
        this.allowedTransitions = allowedTransitions;
    }

    public void validateOrderStatus(OrderStatus orderStatus) {
        if (!this.allowedTransitions.contains(orderStatus))
            throw new InvalidOrderStatusException();
    }
}
