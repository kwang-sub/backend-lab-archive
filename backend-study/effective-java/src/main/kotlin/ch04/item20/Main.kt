package ch04.item20

import java.util.*

fun intArrayAsList(a: IntArray): MutableList<Int> {
    Objects.requireNonNull(a)

    return object : AbstractList<Int>() {
        override val size: Int
            get() = a.size

        override fun get(index: Int): Int {
            return a[index]
        }

        override fun set(i: Int, value: Int): Int {
            val oldVal = a[i]
            a[i] = value
            return oldVal
        }
    }
}