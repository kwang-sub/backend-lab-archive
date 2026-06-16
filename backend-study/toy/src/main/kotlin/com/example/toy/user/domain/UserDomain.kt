package com.example.toy.user.domain

import com.example.toy.user.controller.dto.request.UserCreateDTO
import lombok.Getter

@Getter
class UserDomain(
    private val id: Long? = null,
    private val name: String,
    private val age: Int,
) {
    fun getId() = id
    fun getName() = name
    fun getAge() = age

    companion object {
        fun from(userCreateDTO: UserCreateDTO): UserDomain =
            UserDomain(name = userCreateDTO.name, age = userCreateDTO.age)
    }
}