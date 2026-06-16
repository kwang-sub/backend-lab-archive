package org.example.level2bookmanagementsystem.security.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class AdminController {

    @GetMapping
    fun adminEndpoint(): ResponseEntity<String> {
        return ResponseEntity.ok("Admin access granted")
    }
}