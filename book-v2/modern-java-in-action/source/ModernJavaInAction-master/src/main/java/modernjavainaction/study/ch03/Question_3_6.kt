package modernjavainaction.study.ch03

import java.util.function.Predicate

fun main() {
    val stringToInt: (String) -> Int = String::toInt
    val contains: (List<String>, String) -> Boolean = List<String>::contains
    val startsWithNumber: Predicate<String> = Predicate { str -> str.isNotEmpty() && str[0].isDigit() }
}