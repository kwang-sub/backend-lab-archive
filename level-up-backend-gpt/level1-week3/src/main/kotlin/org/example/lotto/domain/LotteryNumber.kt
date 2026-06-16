package org.example.lotto.domain

open class LotteryNumber(
    val numbers: List<Int>,
) {
    init {
        require(numbers.size == 6) { "로또 번호는 6개입니다." }
        require(numbers.distinct().size == 6) { "로또 번호는 중복될 수 없습니다." }
        require(numbers.all { it in 1..45 }) { "로또 번호는 1부터 45까지의 숫자여야 합니다." }
    }

    private var bonusState: BonusState = BonusState.NotSet

    val bonusNumber: Int
        get() = when (bonusState) {
            is BonusState.NotSet -> throw IllegalStateException("보너스 번호가 설정되지 않았습니다.")
            is BonusState.Set -> (bonusState as BonusState.Set).number
        }

    fun setBonusNumber(bonusNumber: Int) {
        check(bonusState::class != BonusState.Set::class) { "보너스 번호는 한 번만 설정할 수 있습니다." }
        require(bonusNumber in 1..45) { "로또 번호는 1부터 45까지의 숫자여야 합니다." }
        require(!numbers.contains(bonusNumber)) { "로또 번호는 중복될 수 없습니다." }

        this.bonusState = BonusState.Set(bonusNumber)
    }

    fun isComplete(): Boolean {
        return bonusState is BonusState.Set
    }

    override fun toString(): String {
        return "LotteryNumber(numbers=${numbers.joinToString()})"
    }

    fun determineRankBy(winningLotteryNumber: WinningNumbers): Rank {
        val winningNumbers = winningLotteryNumber.numbers
        val winningBonusNumber = winningLotteryNumber.bonusNumber

        val matchedCount = this.numbers.count { winningNumbers.contains(it) }

        return when (matchedCount) {
            6 -> Rank.FIRST
            5 -> {
                if (this.bonusNumber == winningBonusNumber) Rank.SECOND
                else Rank.THIRD
            }
            4 -> Rank.FOURTH
            3 -> Rank.FIFTH
            else -> Rank.NONE
        }
    }

    private sealed class BonusState {
        object NotSet : BonusState()
        data class Set(val number: Int) : BonusState()
    }
}