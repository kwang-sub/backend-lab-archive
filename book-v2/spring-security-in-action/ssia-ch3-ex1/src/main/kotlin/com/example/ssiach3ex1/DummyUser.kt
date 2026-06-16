package com.example.ssiach3ex1

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class DummyUser : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableSetOf(GrantedAuthority { "read" })
    }

    override fun getPassword(): String {
        return "12345"
    }

    override fun getUsername(): String {
        return "bill"
    }

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