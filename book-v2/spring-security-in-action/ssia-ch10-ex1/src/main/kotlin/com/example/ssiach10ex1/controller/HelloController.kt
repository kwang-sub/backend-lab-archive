package com.example.ssiach10ex1.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun getHello(): ResponseEntity<String> {
        return ResponseEntity.ok("Get Hello!")
    }

    @PostMapping("/hello")
    fun postHello(): ResponseEntity<String> {
        return ResponseEntity.ok("Post Hello!")
    }
}