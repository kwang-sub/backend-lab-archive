package ch06.atom73

class IncorrectInputException(
    message: String
) : Exception(message)

fun checkCode(code: Int) {
    if (code <= 1000) throw IncorrectInputException("Code must be > 1000: $code")
}

