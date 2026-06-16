package com.example.ssiach10ex2.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/product")
class ProductController {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/add")
    fun add(@RequestParam name: String): String {
        log.debug("Adding product $name")

        return "main"
    }
}