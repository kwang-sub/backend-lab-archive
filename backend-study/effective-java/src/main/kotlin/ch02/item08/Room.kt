package ch02.item08

import java.lang.ref.Cleaner


class Room(
    private val numJunkPiles: Int
) : AutoCloseable {
    private lateinit var state: State

    @Suppress("Since15")
    private lateinit var cleanable: Cleaner.Cleanable

    init {
        state = State(numJunkPiles)
        cleanable = cleaner.register(this, state)
    }

    override fun close() {
        cleanable.clean()
    }

    private class State(
        var numJunkPlies: Int
    ) : Runnable {
        override fun run() {
            println("방 청소")
            numJunkPlies = 0
        }
    }

    companion object {
        @Suppress("Since15")
        private val cleaner: Cleaner = Cleaner.create()

    }
}
