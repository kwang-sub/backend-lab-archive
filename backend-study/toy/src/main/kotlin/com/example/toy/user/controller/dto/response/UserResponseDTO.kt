package com.example.toy.user.controller.dto.response

import com.example.toy.user.domain.UserDomain

class UserResponseDTO(
    val id: Long?,
    val name: String,
    val age: Int,
) {
    companion object {
        fun from(userDomain: UserDomain): UserResponseDTO =
            UserResponseDTO(id = userDomain.getId(), name = userDomain.getName(), age = userDomain.getAge())
    }
}