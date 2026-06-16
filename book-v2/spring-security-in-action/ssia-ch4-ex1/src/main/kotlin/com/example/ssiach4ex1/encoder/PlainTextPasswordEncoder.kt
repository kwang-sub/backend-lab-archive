package com.example.ssiach4ex1.encoder

import org.springframework.security.crypto.password.PasswordEncoder

class PlainTextPasswordEncoder : PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String {
        return rawPassword.toString()
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return rawPassword == encodedPassword
    }
}