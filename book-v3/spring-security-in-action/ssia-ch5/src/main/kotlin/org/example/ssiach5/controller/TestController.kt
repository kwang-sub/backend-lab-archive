package org.example.ssiach5.controller

import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future

@RestController
class TestController {

    @GetMapping("/hello")
    fun hello(a: Authentication): String {
        val context = SecurityContextHolder.getContext()
        val authentication = context.authentication
        return "hello ${a?.name}!"
    }

    @GetMapping("/bye")
    @Async
    fun bye(a: Authentication): Future<String> {
        val context = SecurityContextHolder.getContext()
        val authentication = context.authentication
        return CompletableFuture.completedFuture("bye ${a?.name}!")
    }
}