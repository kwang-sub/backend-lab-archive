package org.example.market.dto.response;


public record CartResDto(
        Long id,
        Integer quantity,
        ProductResDto product
) {
}
