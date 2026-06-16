package com.example.toy.user.controller.port

import com.example.toy.user.controller.dto.request.UserCreateDTO
import com.example.toy.user.controller.dto.response.UserResponseDTO

interface UserService {

    fun save(userCreateDTO: UserCreateDTO): UserResponseDTO
}