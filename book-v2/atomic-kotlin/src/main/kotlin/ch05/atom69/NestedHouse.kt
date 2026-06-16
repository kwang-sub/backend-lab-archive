package ch05.atom69

abstract class Cleanable(val id: String) {
    open val parts: List<Cleanable> = listOf()
    fun clean(): String {
        val text = "$id clean"
        if (parts.isEmpty()) return text
        return "${parts.joinToString(" ", "(", ")", transform = Cleanable::clean)} $text\n"
    }
}

class House : Cleanable("House") {
    override val parts: List<Cleanable> = listOf()

    class Bedroom(id: String) : Cleanable(id) {
        
    }
}