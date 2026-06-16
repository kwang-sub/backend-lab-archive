package org.example.market.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerReqDto(
        @NotBlank
        String name,
        @NotBlank
        String password
) {
}
