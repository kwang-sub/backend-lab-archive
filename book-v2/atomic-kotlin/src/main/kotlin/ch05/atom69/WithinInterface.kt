package ch05.atom69

interface Item {
    val type: Type
    data class Type(val type: String)
}

class Bolt(type: String) : Item {
    override val type = Item.Type(type)

    override fun toString(): String {
        return "Bolt(type=$type)"
    }
}

fun main() {
    println(Bolt("Slotted"))
    println(Bolt("Hex"))
}