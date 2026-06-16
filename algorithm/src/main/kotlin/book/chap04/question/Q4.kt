package book.chap04.question

class Gstack<E>(private val max: Int) {
    private val stk: ArrayList<E> = arrayListOf()
    private var ptr = 0;


    fun push(e: E): E {
        if (ptr >= max) throw OverflowGException()
        ptr++
        stk.add(e)
        return e
    }

    fun pop(): E {
        if (ptr <= 0) throw EmptyGException()
        return stk[--ptr]
    }

    fun peek(): E {
        if (ptr <= 0) throw EmptyGException()
        return stk[ptr - 1]
    }

    fun indexOf(key: E): Int {
        for (i: Int in ptr - 1 downTo 0 step -1) {
            if (stk[i] == key) return i
        }
        return -1
    }

    fun clear() {
        ptr = 0
    }

    fun capacity(): Int {
        return max
    }

    fun size(): Int {
        return ptr
    }

    fun isEmpty() = stk.isEmpty()

    fun isFull(): Boolean {
        return ptr >= max
    }

    class OverflowGException : RuntimeException()
    class EmptyGException : RuntimeException()
}