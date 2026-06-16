package org.example.market.controller;

import lombok.RequiredArgsConstructor;
import org.example.market.dto.request.CartAddReqDto;
import org.example.market.dto.request.CartModifyReqDto;
import org.example.market.dto.response.CartResDto;
import org.example.market.security.CustomUserDetails;
import org.example.market.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<List<CartResDto>> addCart(
            @RequestBody @Validated CartAddReqDto dto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        return ResponseEntity.ok(cartService.addCart(userDetails.getId(), dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<List<CartResDto>> updateCart(
            @PathVariable Long id,
            @RequestBody @Validated CartModifyReqDto dto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok(cartService.updateCart(id, userDetails.getId(), dto.quantity()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<CartResDto>> deleteCart(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.ok(cartService.deleteCart(id, userDetails.getId()));
    }
}
