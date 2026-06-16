package ch02.atom17

class Cat {
    fun meow() = "mrrrow!"
}

fun main() {
    val cat = Cat()
    val m1 = cat.meow()
    println(m1)
}