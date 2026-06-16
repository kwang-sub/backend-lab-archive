package org.example.market.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record AuthReqDto(@NotEmpty String username, @NotEmpty String password) {
}
