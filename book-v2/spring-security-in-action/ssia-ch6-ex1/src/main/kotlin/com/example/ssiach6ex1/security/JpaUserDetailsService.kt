package com.example.ssiach6ex1.security

import com.example.ssiach6ex1.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JpaUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): CustomUserDetails {
        val user = userRepository.findUserByUsername(username)
            .orElseThrow { UsernameNotFoundException("Problem during authentication!") }

        return CustomUserDetails(user)
    }
}