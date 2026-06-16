package ch04.item19

import java.time.Instant

class Sub: Super() {
    private val instant: Instant = Instant.now()

    override fun overrideMe() {
        println(instant)
    }
}