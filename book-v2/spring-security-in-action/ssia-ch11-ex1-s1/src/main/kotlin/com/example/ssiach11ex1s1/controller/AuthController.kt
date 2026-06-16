package com.example.ssiach11ex1s1.controller

import com.example.ssiach11ex1s1.domain.Otp
import com.example.ssiach11ex1s1.domain.User
import com.example.ssiach11ex1s1.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val userService: UserService,
) {

    @PostMapping("/user/add")
    fun addUser(@RequestBody user: User) {
        userService.addUser(user)
    }

    @PostMapping("/user/auth")
    fun auth(@RequestBody user: User) {
        userService.auth(user)
    }

    @PostMapping("/otp/check")
    fun check(@RequestBody otp: Otp, response: HttpServletResponse) {
        if (userService.check(otp)) response.status = HttpServletResponse.SC_OK
        else response.status = HttpServletResponse.SC_FORBIDDEN
    }
}