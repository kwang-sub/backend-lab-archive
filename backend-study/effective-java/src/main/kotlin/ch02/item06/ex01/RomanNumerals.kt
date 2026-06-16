package ch02.item06.ex01

import java.util.regex.Pattern

class RomanNumerals {
    companion object {
        private val ROMAN: Pattern = Pattern.compile("")

        fun isRomanNumeral(s: String): Boolean = ROMAN.matcher(s).matches()
    }
}