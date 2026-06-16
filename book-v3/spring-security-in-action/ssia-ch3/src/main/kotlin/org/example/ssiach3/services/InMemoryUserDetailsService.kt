package org.example.ssiach3.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class InMemoryUserDetailsService(
    private val users: Collection<UserDetails>,
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return users.firstOrNull { user -> user.username == username }
            ?: throw UsernameNotFoundException(username)
    }
}