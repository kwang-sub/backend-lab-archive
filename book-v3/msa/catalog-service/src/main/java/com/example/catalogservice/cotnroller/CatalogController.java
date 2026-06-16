package com.example.catalogservice.cotnroller;

import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
    private final Environment environment;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in Catalog Service on port " + environment.getProperty("local.server.port");
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getAllCatalogs() {
        return ResponseEntity.ok(catalogService.getAllCatalogs());
    }
}
