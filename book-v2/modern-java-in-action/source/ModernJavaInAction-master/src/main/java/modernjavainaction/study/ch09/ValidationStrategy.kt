package modernjavainaction.study.ch09

fun interface ValidationStrategy {
    fun execute(s: String): Boolean
}

class IsAllLowerCase : ValidationStrategy {
    override fun execute(s: String): Boolean {
        return s.matches(Regex("[a-z]+"))
    }
}

class IsNumeric : ValidationStrategy {
    override fun execute(s: String): Boolean {
        return s.matches(Regex("\\d+"))
    }
}


class Validator(
    private val strategy: ValidationStrategy
) {
    fun validate(s: String): Boolean {
        return strategy.execute(s)
    }
}

fun main() {
    val numericValidator = Validator { it.matches(Regex("\\d+")) }
    val b1 = numericValidator.validate("123")

    val lowerCaseValidator = Validator(IsAllLowerCase())
    val b2 = lowerCaseValidator.validate("aaa")

    println(b1)
    println(b2)
}