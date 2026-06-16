package org.example.baseball.view

import org.example.baseball.constant.ErrorMessage

class Validator {
    fun validateBaseballNumber(input: String?, delimiters: String) {
        require(input != null) { ErrorMessage.INVALID_BASEBALL_NUMBER }
        require(input.matches(Regex("^[1-9]+$delimiters[1-9]+$delimiters[1-9]+\$"))) { ErrorMessage.INVALID_BASEBALL_NUMBER }
        require(input.split(delimiters).distinct().size == 3) { ErrorMessage.DUPLICATE_BASEBALL_NUMBER }
    }
}