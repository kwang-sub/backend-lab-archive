package com.example.toy.user.controller

import com.example.toy.user.controller.dto.request.UserCreateDTO
import com.example.toy.user.controller.dto.response.UserResponseDTO
import com.example.toy.user.controller.port.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping(path = ["/user"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody userCreateDTO: UserCreateDTO): ResponseEntity<UserResponseDTO> {
        logger.debug("user sava dto = {}", userCreateDTO)
        val result = userService.save(userCreateDTO)
        return ResponseEntity.ok(result)
    }
}