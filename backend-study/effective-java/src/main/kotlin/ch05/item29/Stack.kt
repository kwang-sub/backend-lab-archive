package ch05.item29

import java.util.*

class Stack<E>(
) {
    private var elements: MutableList<E> = mutableListOf()
    private val size: Int
        get() = elements.size

    fun push(e: E) {
        elements.add(e)
    }

    fun pushAll(src: Iterable<out E>) {
        for (e in src) {
            push(e)
        }
    }

    fun pop(): E {
        if (size == 0) throw EmptyStackException()
        return elements.removeAt(size - 1)
    }

    fun popAll(dst: MutableCollection<in E>) {
        while (!isEmpty()) {
            dst.add(pop())
        }
    }

    fun isEmpty(): Boolean = size == 0
    override fun toString(): String {
        return "Stack(elements=$elements, size=$size)"
    }

}

fun main() {
    val numberStack: Stack<Number> = Stack()
    val integers: Iterable<Int> = listOf(1, 2, 3)
    numberStack.pushAll(integers)
    println(numberStack.toString())

    val test = mutableListOf<Any>()
    numberStack.popAll(test)
    println(test)
}