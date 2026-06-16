@file:JvmName("StringFunctions")

package strings

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
): String {
    val result = StringBuilder(prefix)
    for ((index, value) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(value)
    }
    result.append(postfix)
    return result.toString()
}

fun Collection<String>.join (
    separator: String = " ",
    prefix: String = "",
    postfix: String = "",
) = joinToString(separator = separator, prefix = prefix, postfix = postfix)