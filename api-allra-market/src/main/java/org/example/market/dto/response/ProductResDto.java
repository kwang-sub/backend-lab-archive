package org.example.market.dto.response;

import java.time.LocalDateTime;

public record ProductResDto(
        Long id,
        String name,
        String description,
        Long price,
        Integer stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
