package ch04.item18

class InstrumentedSet<E>(
    private val _set: MutableSet<E> = mutableSetOf()
): ForwardingSet<E>(_set) {
    private var addCount: Int = 0
    val set: MutableSet<E>
        get() = _set
    override fun add(e: E): Boolean {
        addCount++
        return super.add(e)
    }


    override fun addAll(c: Collection<out E>): Boolean {
        addCount += c.size
        return super.addAll(c)
    }

    fun getAddCount(): Int = addCount

}