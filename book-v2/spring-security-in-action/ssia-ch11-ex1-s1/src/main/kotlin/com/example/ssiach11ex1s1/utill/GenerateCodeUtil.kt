package com.example.ssiach11ex1s1.utill

import java.security.SecureRandom

object GenerateCodeUtil {
    fun generateCode(): String {
        val random = SecureRandom.getInstanceStrong()
        val c = random.nextInt(9000) + 1000

        return c.toString()
    }

}
