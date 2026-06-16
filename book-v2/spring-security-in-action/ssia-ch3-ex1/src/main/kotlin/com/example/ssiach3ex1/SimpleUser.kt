package com.example.ssiach3ex1

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SimpleUser(
    private val username: String,
    private val password: String,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableSetOf(GrantedAuthority { "read" })

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}