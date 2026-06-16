package ex02

import ex01.Scoreable

data class Criterion(
    var answer: Answer,
    var weight: Weight,
    var scoreValue: Int = 0,
) : Scoreable {
    override fun getScore(): Int = scoreValue
}