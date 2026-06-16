package ch03.atom41

data class Automobile(
    val brand: String
) {
}

class RigidHolder(private val a: Automobile) {
    fun getValue() = a
}

fun main() {
    val holder = RigidHolder(Automobile("BMW"))
    println(holder.getValue())
}
