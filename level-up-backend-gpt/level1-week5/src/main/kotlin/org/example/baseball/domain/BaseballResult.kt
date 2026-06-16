package org.example.baseball.domain

import org.example.baseball.constant.ErrorMessage

class BaseballResult(
    val strikeCount: Int,
    val ballCount: Int
) {

    init {
        require(strikeCount in 0..3) { ErrorMessage.INVALID_BASEBALL_RESULT_COUNT }
        require(ballCount in 0..3) { ErrorMessage.INVALID_BASEBALL_RESULT_COUNT }
        require(strikeCount + ballCount <= 3) { ErrorMessage.INVALID_BASEBALL_RESULT_COUNT }
    }

    fun isThreeStrike(): Boolean {
        return strikeCount == 3
    }

}