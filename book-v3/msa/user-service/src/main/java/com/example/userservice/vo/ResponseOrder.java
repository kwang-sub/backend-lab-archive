package com.example.userservice.vo;

import java.util.Date;

public record ResponseOrder(
        String productId,
        Integer qty,
        Integer unitPrice,
        Integer totalPrice,
        Date createdAt,
        String orderId
) {
}
