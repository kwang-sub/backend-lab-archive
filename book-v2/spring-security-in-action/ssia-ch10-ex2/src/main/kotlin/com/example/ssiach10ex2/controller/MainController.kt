package com.example.ssiach10ex2.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/main")
    fun main(): String {

        return "main"
    }
}