package com.example.toy.user.service

import com.example.toy.user.controller.dto.request.UserCreateDTO
import com.example.toy.user.controller.dto.response.UserResponseDTO
import com.example.toy.user.controller.port.UserService
import com.example.toy.user.domain.UserDomain
import com.example.toy.user.service.port.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {

    @Transactional
    override fun save(userCreateDTO: UserCreateDTO): UserResponseDTO {
        val userDomain = UserDomain.from(userCreateDTO)
        val saveUserDomain = userRepository.save(userDomain)
        return UserResponseDTO.from(saveUserDomain)
    }
}