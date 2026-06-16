package ch08

fun main() {
    val sum1: (Int, Int) -> Int = { x, y -> x + y }
    val sum2 = { x: Int, y: Int -> x + y }

    val action = { println(42) }

    val action2: () -> Unit = { println(42) }

    var canReturnNull: (Int, Int) -> Int? = { x, y -> if (x + y >= 10) x + y else null }

    var funOrNull: ((Int, Int) -> Int)? = null
    funOrNull = { x, y -> x / y }

    twoAndThree(operation = { a: Int, b: Int -> a * b })

    println("ab1sd".filter { it in 'a'..'c' })

    val letters = listOf("Alpha", "Beta")
    println(letters.joinToString())
    println(letters.joinToString { it.lowercase() })
    println(letters.joinToString(
        separator = "||",
        prefix = "Start ",
        postfix = " End"
        ) { it.lowercase() })

}

fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2, 3)
    println("The result is $result")
}

fun test() {
    return twoAndThree(operation = { a: Int, b: Int -> a * b })
}

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in indices) {
        val element = this[index]
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: ((T) -> String)? = null,
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        val str = transform?.invoke(element) ?: element.toString()
        result.append(str)
    }
    result.append(postfix)

    return result.toString()
}