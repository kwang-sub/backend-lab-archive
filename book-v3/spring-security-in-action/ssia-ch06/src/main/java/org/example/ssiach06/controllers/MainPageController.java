package org.example.ssiach06.controllers;

import java.util.List;
import org.example.ssiach06.entities.Product;
import org.example.ssiach06.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {

    @Autowired
    private ProductService productService;

    @GetMapping("/main")
    public ResponseEntity<List<Product>> main() {
        return ResponseEntity.ok(productService.findAll());
    }
}
