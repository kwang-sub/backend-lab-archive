package ch02.atom20

class Counter(
    var start: Int
) {
    fun increment() {
        start += 1
    }

    override fun toString(): String {
        return "Counter(start=$start)"
    }
}

class CounterHolder(counter: Counter) {
    private val ctr = counter
    override fun toString(): String {
        return "CounterHolder(ctr=$ctr)"
    }
}

fun main() {
    val c = Counter(11)
    val ch = CounterHolder(c)
    println(ch)

    c.increment()
    println(ch)

    val ch2 = CounterHolder(Counter(9))
    println(ch2)
}