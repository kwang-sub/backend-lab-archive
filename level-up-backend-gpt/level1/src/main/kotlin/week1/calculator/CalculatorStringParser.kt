package week1.calculator

class CalculatorStringParser {
    private val SUM_CHARS = listOf(",", ";")
    private val NUM_REGEX = Regex("""-?\d+""")
    private val OPERATOR_REGEX = Regex("""[+\-*/]""")
    private val SUFFIX_CUSTOM_STR = "\n"
    private val PREFIX_CUSTOM_STR = "//"

    fun parse(input: String): List<String> {
        val customInputFlag = input.length > PREFIX_CUSTOM_STR.length + 1 + SUFFIX_CUSTOM_STR.length && isCustomInput(input)

        val customOpChar = if (customInputFlag) getCustomOpChar(input) else null
        val input = if (customInputFlag && customOpChar != null) getCustomInput(input, customOpChar) else input
        val tokens = input.split(" ")

        if (tokens.isEmpty() || tokens.size % 2 == 0)
            throw IllegalArgumentException("잘못된 입력입니다.")

        return tokens.mapIndexed { index, token ->
            if (index % 2 == 0) {
                if (!token.matches(NUM_REGEX))
                    throw IllegalArgumentException("잘못된 입력입니다.")
            } else {
                if (SUM_CHARS.contains(token)) return@mapIndexed "+"
                if (!token.matches(OPERATOR_REGEX))
                    throw IllegalArgumentException("잘못된 입력입니다.")
            }
            token
        }
    }

    private fun getCustomInput(input: String, operationChar: String): String {
        return input.substring(PREFIX_CUSTOM_STR.length + 1 + SUFFIX_CUSTOM_STR.length, input.length)
            .replace(operationChar, " + ")
    }

    private fun getCustomOpChar(input: String): String {
        return input.substring(PREFIX_CUSTOM_STR.length, PREFIX_CUSTOM_STR.length + 1)
    }

    private fun isCustomInput(input: String): Boolean {
        val inputCustomSuffixStr = input.substring(
            PREFIX_CUSTOM_STR.length + 1,
            PREFIX_CUSTOM_STR.length + 1 + SUFFIX_CUSTOM_STR.length
        )
        return input.startsWith(PREFIX_CUSTOM_STR) || inputCustomSuffixStr == SUFFIX_CUSTOM_STR
    }
}