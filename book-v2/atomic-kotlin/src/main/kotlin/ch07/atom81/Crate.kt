package ch07.atom81

open class Crate<T>(private var contents: T) {
    fun put(item: T) {
        contents = item
    }

    fun get(): T = contents
}

fun main() {
    val cc = Crate(Car())
    val car = cc.get()
    println(car)
}