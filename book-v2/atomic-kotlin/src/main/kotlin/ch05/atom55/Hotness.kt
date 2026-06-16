package ch05.atom55

interface Hotness {
    fun feedback(): String
}

enum class SpiceLevel : Hotness {
    Mild {
        override fun feedback(): String {
            TODO("Not yet implemented")
        }
    }
}