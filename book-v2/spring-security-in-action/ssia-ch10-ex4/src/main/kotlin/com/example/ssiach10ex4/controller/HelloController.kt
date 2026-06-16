package com.example.ssiach10ex4.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @PostMapping("/hello")
    fun postHello() = "Post Hello!"

    @PostMapping("/ciao")
    fun postCiao() = "Post Ciao"

    @GetMapping("/hello")
    fun getHello(): String = "Get Hello!"

}