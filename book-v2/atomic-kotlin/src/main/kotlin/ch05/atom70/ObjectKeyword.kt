package ch05.atom70

object JustOne {
    val n = 2
    fun f() = n * 10
    fun g() = this.n * 20
}

fun main() {
    println(JustOne.n)
    println(JustOne.f())
    println(JustOne.g())
}