package ch02.item07

import java.util.*

class Stack(
    private var elements: Array<Any?>
) {
    private var size: Int = 0

    fun push(e: Any) {
        ensureCapacity()
        elements[size++] = e
    }

    private fun ensureCapacity() {
        if (elements.size == size)
            elements = arrayOf(elements.copyOf(2 * size + 1))
    }

    fun pop(): Any? {
        if (size == 0)
            throw EmptyStackException()
        val result = elements[--size]
        elements[size] = null
        return result
    }

    companion object {
        const val DEFAULT_INITIAL_CAPACITY: Int = 16
    }
}