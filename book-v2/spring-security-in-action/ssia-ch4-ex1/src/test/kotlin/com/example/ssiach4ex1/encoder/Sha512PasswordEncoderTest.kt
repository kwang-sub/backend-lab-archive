package com.example.ssiach4ex1.encoder

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Sha512PasswordEncoderTest {

    private val encoder: Sha512PasswordEncoder = Sha512PasswordEncoder()

    @Test
    fun encode() {
        val encode = encoder.encode("test")
        Assertions.assertThat(encode).isNotEqualTo("test")
    }

    @Test
    fun matches() {
        val encode = encoder.encode("test")
        Assertions.assertThat(encoder.matches("test", encode)).isTrue()
    }
}