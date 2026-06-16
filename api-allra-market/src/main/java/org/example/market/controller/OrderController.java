package org.example.market.controller;

import lombok.RequiredArgsConstructor;
import org.example.market.dto.request.OrderReqDto;
import org.example.market.dto.response.OrderResDto;
import org.example.market.security.CustomUserDetails;
import org.example.market.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(
            @RequestBody @Validated OrderReqDto dto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        OrderResDto result = orderService.createOrderWithPayment(userDetails.getId(), dto);
        return switch (result.orderStatus()) {
            case PAYMENT_FAILED -> ResponseEntity.internalServerError()
                    .body(ProblemDetail.forStatusAndDetail(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Payment failed while creating the order. Status: " + result.orderStatus().name()));
            default -> ResponseEntity.ok(result);
        };
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<OrderResDto>> getCompletedOrders(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Pageable page
    ) {
        return ResponseEntity.ok(orderService.getCompletedOrders(userDetails.getId(), page));
    }
}
