package org.example.lotto.domain

class LotteryBundle(
    val amount: Int,
    val lotteryTicketList: List<LotteryTicket>
) {
    fun getLotteryInfoGroupByRank(): Map<Rank?, List<LotteryTicket>> {
        return lotteryTicketList.groupBy { it.rank }
    }

    fun getRevenueAmount(): Int {
        return lotteryTicketList.sumOf { it.rank?.getPrize() ?: 0 }
    }

    fun getRevenueRate(): Double {
        return if (amount == 0) 0.0 else getRevenueAmount().toDouble() / amount * 100
    }

    init {
        require(
            amount > 0 &&
                    amount % AMOUNT == 0 &&
                    lotteryTicketList.size == amount / AMOUNT
        ) { "로또 금액 또는 수량이 올바르지 않습니다." }

        require(lotteryTicketList.distinctBy { it.order }.size == lotteryTicketList.size) { "로또 번호는 중복될 수 없습니다." }
    }

    companion object {
        const val AMOUNT = 1_000
    }
}