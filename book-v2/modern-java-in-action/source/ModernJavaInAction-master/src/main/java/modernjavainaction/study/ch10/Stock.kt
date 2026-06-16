package modernjavainaction.study.ch10

data class Stock(
    var symbol: String? = null,
    var market: String? = null,
)

data class Trade(
    var type: Type? = null,
    var stock: Stock? = null,
    var quantity: Int? = null,
    var price: Double? = null,
) {

    fun getValue() = quantity?.let { quantity ->  price?.let { quantity * it } }
    companion object {
        enum class Type {
            BUY, SELL
        }
    }
}

data class Order(
    var customer: String? = null,
    val trades: MutableList<Trade> = mutableListOf()
) {
    fun addTrade(trade: Trade) {
        trades.add(trade)
    }

    fun getValue() = trades.mapNotNull { it.getValue() }.sumOf { it }
}


fun main() {
    val order = Order("BigBank")
    val stock1 = Stock(
        symbol = "IBM",
        market = "NYSE"
    )

    val trade1 = Trade(
        type = Trade.Companion.Type.BUY,
        price = 125.00,
        quantity = 80,
        stock = stock1
    )
    order.addTrade(trade1)

}