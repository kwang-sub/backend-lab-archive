package org.example.ssiach16ex1.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.ssiach16ex1.model.Product;
import org.example.ssiach16ex1.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/sell")
    public ResponseEntity<List<Product>> sellProduct() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("bear", "natalie"));
        products.add(new Product("candy", "natalie"));
        products.add(new Product("chocolate", "emma"));

        return ResponseEntity.ok(productService.sellProducts(products));
    }
}
