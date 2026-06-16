package ch05.atom65

interface Controls {
    fun up(velocity: Int): String
    fun down(velocity: Int): String
    fun left(velocity: Int): String
    fun right(velocity: Int): String
    fun forward(velocity: Int): String
    fun back(velocity: Int): String
    fun turboBoost(velocity: Int): String

}