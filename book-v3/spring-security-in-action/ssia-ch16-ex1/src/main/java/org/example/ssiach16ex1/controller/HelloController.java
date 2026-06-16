package org.example.ssiach16ex1.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.ssiach16ex1.service.NameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final NameService nameService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello " + nameService.getName();
    }

    @GetMapping("/secret/names/{name}")
    public ResponseEntity<List<String>> secretNames(@PathVariable String name) {
        return ResponseEntity.ok(nameService.getSecretNames(name));
    }
}
