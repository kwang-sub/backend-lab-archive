package com.example.ssiach5ex2.controller

import org.springframework.scheduling.annotation.Async
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable
import java.util.concurrent.Executors

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        val context = SecurityContextHolder.getContext()

        val authentication = context.authentication
        return "hello, ${authentication.name}!"
    }

    @GetMapping("/bye")
    @Async
    fun goodbye() {
        val context = SecurityContextHolder.getContext()
        val name = context.authentication.name
        println(name)
    }

    @GetMapping("/ciao")
    fun ciao(): String {
        val task: Callable<String> = Callable {
            val context = SecurityContextHolder.getContext()
            context.authentication.name
        }
        val e = Executors.newCachedThreadPool()
        try {
            return "Ciao, ${e.submit(task).get()}!"
        } finally {
            e.shutdown()
        }
    }

    @GetMapping("/hola")
    fun hola(): String {
        val task: Callable<String> = Callable { SecurityContextHolder.getContext().authentication.name }
        var e = Executors.newCachedThreadPool()
        e = DelegatingSecurityContextExecutorService(e)
        try {
            return "Hola, ${e.submit(task).get()}!"
        } finally {
            e.shutdown()
        }

    }
}