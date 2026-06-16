package com.example.ssiach4ex1.encoder

import org.springframework.security.crypto.password.PasswordEncoder
import java.security.MessageDigest

class Sha512PasswordEncoder : PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String {
        return hashWithSHA512(rawPassword.toString())
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return encodedPassword == encode(rawPassword)
    }

    private fun hashWithSHA512(input: String): String {
        val result = StringBuilder()
        val digested = MessageDigest.getInstance("SHA-512").digest(input.toByteArray())
        for (b: Byte in digested) {
            result.append(Integer.toHexString(0xFF + b))
        }
        return result.toString()
    }
}