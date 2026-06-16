package com.example.ssiach6ex1.controller

import com.example.ssiach6ex1.service.ProductService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController(
    private val productService: ProductService,
) {

    @GetMapping("/main")
    fun main(a: Authentication, model: Model): String {
        model.addAttribute("username", a.name)
        model.addAttribute("products", productService.findAll())

        return "main"
    }
}