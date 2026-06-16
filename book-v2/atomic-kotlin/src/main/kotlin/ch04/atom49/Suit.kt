package ch04.atom49

import kotlin.random.Random

enum class Suit {
    Spade, Club, Heart, Diamond
}

enum class Rank(val faceValue: Int) {
    Ace(1), Two(2), Three(3), Four(4), Five(5)
}

class Card(val rank: Rank, val suit: Suit) {
    override fun toString(): String {
        return "Card(rank=$rank, suit=$suit)"
    }
}

val deck: List<Card> =
    Suit.values().flatMap { suit ->
        Rank.values().map { Card(it, suit) }
    }


fun main() {
    val rand = Random(26)
    repeat(7) { println(deck.random(rand)) }
}