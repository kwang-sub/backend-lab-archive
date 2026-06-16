package org.example.market.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartAddReqDto(
        @NotNull
        Long productId,
        @NotNull
        @Min(value = 1)
        Integer quantity
) {
}
