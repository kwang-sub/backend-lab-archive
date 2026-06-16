package ch08

enum class Delivery(
    val code: String,
) {
    STANDARD("01"), EXPEDITED("02"),
}

class Order(val itemCount: Int)

fun getShippingCostCalculator(
    delivery: Delivery,
): (Order) -> Double {
    return when (delivery) {
        Delivery.EXPEDITED -> { order -> 6 + 2.1 * order.itemCount }
        Delivery.STANDARD -> { order -> 1.2 * order.itemCount }
    }
}