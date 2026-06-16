package com.example.ssiach8ch1.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello!"
    }

    @GetMapping("/ciao")
    fun ciao(): String {
        return "Ciao!"
    }

    @GetMapping("/hola")
    fun hola(): String {
        return "hola!"
    }
}