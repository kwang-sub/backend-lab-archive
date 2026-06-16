package ch08.item55

import java.util.*

fun <E : Comparable<E>> max(c: Collection<E>): Optional<E> {
    if (c.isEmpty()) return Optional.empty()

    var result: E? = null
    for (e: E in c) {
        if (result == null || e.compareTo(result) > 0) result = Objects.requireNonNull(e)
    }

    return if (result != null) Optional.of(result) else Optional.empty()
}