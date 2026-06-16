package ch05.atom55

interface Player {
    val symbol: Char
}

class Food(
    override val symbol: Char
) : Player {
}

