package org.example.market.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

public record OrderCartReqDto(
        @NotNull
        Long cartId,
        @NotNull
        Long productId,
        @NotNull
        Integer quantity,
        @NotNull
        BigDecimal price
) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderCartReqDto that)) return false;
        return Objects.equals(cartId, that.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cartId);
    }
}
