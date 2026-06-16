package ch03.atom41

class GenericHolder<T>(
    private val value: T
) {
    fun getValue(): T = value
}

fun main() {
    val h1 = GenericHolder(Automobile("Ford"))
    val a = h1.getValue()
    println(a)
    println(h1.getValue())

    val h2 = GenericHolder("test")
    println(h2.getValue())
}