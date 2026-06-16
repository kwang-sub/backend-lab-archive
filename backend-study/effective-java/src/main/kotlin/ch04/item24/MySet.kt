package ch04.item24

class MySet<E>: AbstractSet<E>() {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun iterator(): Iterator<E> {
        return MyIterator()
    }

    private class MyIterator<E> : Iterator<E> {
        override fun hasNext(): Boolean {
            TODO("Not yet implemented")
        }

        override fun next(): E {
            TODO("Not yet implemented")
        }
    }
}