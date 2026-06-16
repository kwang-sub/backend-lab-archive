package org.example.lotto.domain

class LotteryTicket(
    val lotteryNumber: LotteryNumber,
    val order: Int
) {
    var _rank: Rank? = null
    val rank: Rank?
        get() = _rank
        private set

    fun setRank(rank: Rank) {
        require(_rank == null) { "이미 등급이 설정되어 있습니다." }
        _rank = rank
    }

    override fun toString(): String {
        return if (rank == null) "${order}번째 로또 번호는 ${lotteryNumber}입니다."
        else "${order}번째 로또 번호는 ${lotteryNumber}입니다. (등급: ${rank})"
    }
}