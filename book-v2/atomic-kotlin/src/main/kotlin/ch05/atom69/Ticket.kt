package ch05.atom69

import ch05.atom69.Ticket.Seat.*

class Ticket(
    val name: String,
    val seat: Seat = Coach
) {
    enum class Seat {
        Coach, Premium, Business, First
    }

    fun upgrade(): Ticket {
        val newSeat = entries[(seat.ordinal + 1).coerceAtMost(First.ordinal)]
        return Ticket(name, newSeat)
    }

    fun meal() = when (seat) {
        Coach -> "Bag Meal"
        Premium -> "Bag Meal with Cookie"
        Business -> "Hot Meal"
        First -> "Private Chef"
    }

    override fun toString(): String {
        return "Ticket(seat=$seat)"
    }

}

fun main() {
    println(Ticket("Jerry"))
    println(Ticket("Jerry").upgrade())
    println(Ticket("Jerry").meal())
}