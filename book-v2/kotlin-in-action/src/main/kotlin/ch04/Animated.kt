package ch04

abstract class Animated {

    abstract fun animate()

    open fun stopAnimating() {}

    fun animateTwice() {}
}