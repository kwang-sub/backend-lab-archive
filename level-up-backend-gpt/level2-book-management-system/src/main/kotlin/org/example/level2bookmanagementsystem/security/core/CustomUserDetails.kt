package org.example.level2bookmanagementsystem.security.core

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val user: User
): UserDetails {

    // 사용자의 권한 목록을 반환
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return user.authorities
    }

    // 사용자의 비밀번호를 반환
    override fun getPassword(): String {
        return user.password
    }

    // 사용자의 이름(아이디)을 반환
    override fun getUsername(): String {
        return user.username
    }

    // 계정이 만료되지 않았는지 여부 반환
    override fun isAccountNonExpired(): Boolean {
        return super.isAccountNonExpired()
    }

    // 계정이 잠겨있지 않은지 여부 반환
    override fun isAccountNonLocked(): Boolean {
        return super.isAccountNonLocked()
    }

    // 자격 증명이 만료되지 않았는지 여부 반환
    override fun isCredentialsNonExpired(): Boolean {
        return super.isCredentialsNonExpired()
    }

    // 계정이 활성화되어 있는지 여부 반환
    override fun isEnabled(): Boolean {
        return super.isEnabled()
    }
}