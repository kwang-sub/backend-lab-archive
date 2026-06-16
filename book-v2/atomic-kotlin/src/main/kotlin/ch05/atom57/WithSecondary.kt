package ch05.atom57

class WithSecondary(i: Int) {
    init {
        println("Primary: $i")
    }

    constructor(c: Char) : this(c - 'A') {
        println("Secondary: $c")
    }
}

fun main() {
    fun sep() = println("=============================")

    WithSecondary(1)
    sep()
    WithSecondary('D')
    sep()
}