package modernjavainaction.study.ch10

import modernjavainaction.base.chap10.dsl.model.Order
import modernjavainaction.base.chap10.dsl.model.Stock
import modernjavainaction.base.chap10.dsl.model.Trade


class MethodChainingOrderBuilder private constructor(customer: String) {
    val order = Order()

    init {
        order.customer = customer
    }

    fun end(): Order {
        return order
    }

    fun buy(quantity: Int): TradeBuilder {
        return TradeBuilder(this, Trade.Type.BUY, quantity)
    }

    fun sell(quantity: Int): TradeBuilder {
        return TradeBuilder(this, Trade.Type.SELL, quantity)
    }

    private fun addTrade(trade: Trade): MethodChainingOrderBuilder {
        order.addTrade(trade)
        return this
    }

    class TradeBuilder(
        private val builder: MethodChainingOrderBuilder,
        type: Trade.Type,
        quantity: Int
    ) {
        val trade = Trade()

        init {
            trade.type = type
            trade.quantity = quantity
        }

        fun stock(symbol: String): StockBuilder {
            return StockBuilder(builder, trade, symbol)
        }
    }

    class TradeBuilderWithStock(private val builder: MethodChainingOrderBuilder, private val trade: Trade) {
        fun at(price: Double): MethodChainingOrderBuilder {
            trade.price = price
            return builder.addTrade(trade)
        }
    }

    class StockBuilder(
        private val builder: MethodChainingOrderBuilder,
        private val trade: Trade,
        symbol: String
    ) {
        private val stock = Stock()

        init {
            stock.symbol = symbol
        }

        fun on(market: String?): TradeBuilderWithStock {
            stock.market = market
            trade.stock = stock
            return TradeBuilderWithStock(builder, trade)
        }
    }

    companion object {
        fun forCustomer(customer: String): MethodChainingOrderBuilder {
            return MethodChainingOrderBuilder(customer)
        }
    }
}

