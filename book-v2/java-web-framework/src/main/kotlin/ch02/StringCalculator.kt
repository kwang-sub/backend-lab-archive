package ch02

import java.util.regex.Pattern

class StringCalculator {

    fun add(str: String?): Long {
        if (str.isNullOrBlank()) return 0
        return split(str).sumOf(::toPositiveLong)
    }

    private fun split(str: String): List<String> {
        val baseDelimiters = mutableListOf(",", ":")

        val m = Pattern.compile("//(.)\n(.*)").matcher(str)
        if (m.find()) {
            val customDelimiter = m.group(1)
            return m.group(2).split(customDelimiter)
        }
        return str.split(*baseDelimiters.toTypedArray())
    }

    private fun toPositiveLong(str: String): Long {
        return str.toLongOrNull()
            ?.also { if (it < 0L) throw RuntimeException() }
            ?: throw RuntimeException()
    }
}