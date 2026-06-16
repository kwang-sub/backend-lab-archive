package ch04.item18

open class ForwardingSet<E>(
    private val s: MutableSet<E>
): MutableSet<E> {
    override fun add(element: E): Boolean {
        return s.add(element)
    }

    override fun addAll(elements: Collection<E>): Boolean {
        return s.addAll(elements)
    }

    override fun clear() = s.clear()

    override fun iterator(): MutableIterator<E> {
        return s.iterator()
    }

    override fun remove(element: E): Boolean {
        return s.remove(element)
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        return s.removeAll(elements)
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        return s.retainAll(elements)
    }

    override val size: Int
        get() = s.size

    override fun contains(element: E): Boolean {
        return s.contains(element)
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return s.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return s.isEmpty()
    }
}