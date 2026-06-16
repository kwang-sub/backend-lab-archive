package modernjavainaction.study.ch10

class NestedFunctionOrderBuilder {
    companion object {
        fun order(customer: String, vararg trades: Trade): Order {
            val order = Order()
            order.customer = customer
            trades.forEach { order.addTrade(it) }
            return order
        }

        fun buy(quantity: Int, stock: Stock, price: Double): Trade {
            return buildTrade(quantity, stock, price, Trade.Companion.Type.BUY)
        }

        fun sell(quantity: Int, stock: Stock, price: Double): Trade {
            return buildTrade(quantity, stock, price, Trade.Companion.Type.SELL)
        }

        fun buildTrade(quantity: Int, stock: Stock, price: Double, buy: Trade.Companion.Type): Trade {
            return Trade().apply {
                this.quantity = quantity
                this.stock = stock
                this.price = price
                this.type = buy
            }
        }

        fun at(price: Double) = price

        fun stock(symbol: String, market: String): Stock {
            return Stock()
                .apply {
                    this.symbol = symbol
                    this.market = market
                }
        }

        fun on(market: String) = market
    }
}