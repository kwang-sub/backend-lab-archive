package org.example.market.controller;

import lombok.RequiredArgsConstructor;
import org.example.market.dto.response.ProductResDto;
import org.example.market.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResDto>> getProducts(Pageable page) {
        return ResponseEntity.ok(productService.getPage(page));
    }
}
