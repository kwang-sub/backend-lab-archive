package org.example.car

import java.security.SecureRandom

class RandomNumGenerator {

    private val secureRandom = SecureRandom()

    fun generate(): Int {
        return secureRandom.nextInt(0, 10)
    }
}