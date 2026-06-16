package ch04.item18

class InstrumentedHashSet<T> : HashSet<T> {
    private var addCount: Int = 0

    constructor() : super()
    constructor(initCap: Int, loadFactor: Float) : super(initCap, loadFactor)

    override fun add(t: T): Boolean {
        addCount++
        return super.add(t)
    }

    override fun addAll(c: Collection<out T>): Boolean {
        addCount += c.size
        return super.addAll(c)
    }

    fun getAddCount(): Int = addCount
}