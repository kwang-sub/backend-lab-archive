package modernjavainaction.study.ch03

import java.util.function.Predicate

fun main() {
    val nonBlankStringPredicate: (String) -> Boolean = { s -> s.isNotBlank() }
    val result = filter(listOf(" ", "test"), nonBlankStringPredicate)
    println(result)
    var portNumber = 1337
    val r: () -> Unit = {
        portNumber = 10
        println("portNum = $portNumber")
    }
    portNumber = 31337
    r()
    println(portNumber)

    filter(listOf("test"), ::isValidName)
}

fun <T> filter(target: List<T>, p: Predicate<T>): List<T> {
    val result = mutableListOf<T>()
    for (item in target) {
        if (p.test(item)) result.add(item)
    }
    return result
}

fun isValidName(string: String): Boolean {
    return string[0].isUpperCase()
}
