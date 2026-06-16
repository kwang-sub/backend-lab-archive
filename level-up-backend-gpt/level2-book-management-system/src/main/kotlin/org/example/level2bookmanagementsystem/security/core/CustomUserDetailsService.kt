package org.example.level2bookmanagementsystem.security.core

import org.example.level2bookmanagementsystem.base.repository.UserRepository
import org.example.level2bookmanagementsystem.swagger.exception.EntityNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userRepository.findByUserLoginId(username)
            ?: throw EntityNotFoundException(User::class)

        val user = User(
            userEntity.userLoginId,
            userEntity.password,
            listOf(SimpleGrantedAuthority(userEntity.role.name))
        )

        return CustomUserDetails(user)
    }
}