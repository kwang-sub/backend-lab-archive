package org.example.market.controller;

import lombok.RequiredArgsConstructor;
import org.example.market.dto.request.CustomerReqDto;
import org.example.market.dto.response.CustomerResDto;
import org.example.market.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResDto> createCustomer(@RequestBody @Validated final CustomerReqDto dto) {
        return ResponseEntity.ok(customerService.create(dto.name(), dto.password()));
    }
}
