package com.example.ssiach8ch1.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @PostMapping("/a")
    fun postEndpointA(): String = "Works!"

    @GetMapping("/a")
    fun getEndpointA(): String = "Works!"

    @GetMapping("/a/b")
    fun getEndPointB(): String = "Works!"

    @GetMapping("/a/b/c")
    fun getEndPointC(): String  = "Works!"
}