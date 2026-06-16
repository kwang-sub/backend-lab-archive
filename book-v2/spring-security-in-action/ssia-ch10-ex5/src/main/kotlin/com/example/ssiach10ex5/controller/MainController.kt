package com.example.ssiach10ex5.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
//@CrossOrigin("http://localhost:8080")
class MainController {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/")
    fun main(): String = "main"

    @PostMapping("/test")
    @ResponseBody
    fun test(): String {
        log.debug("Test method called")
        return "HELLO"
    }
}