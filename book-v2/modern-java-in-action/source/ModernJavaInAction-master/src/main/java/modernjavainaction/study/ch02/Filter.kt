package modernjavainaction.study.ch02

import java.util.function.Predicate

fun <T> MutableList<T>.filter(p: Predicate<T>): MutableList<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (p.test(item)) result.add(item)
    }
    return result
}