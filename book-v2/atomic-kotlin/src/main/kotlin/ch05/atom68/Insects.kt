package ch05.atom68

interface Insect {
    val name: String
    fun walk() = "$name: walk"
    fun fly() = "$name: fly"
}

class HouseFly(override val name: String) : Insect

class Flea(override val name: String) : Insect {
    override fun fly(): String {
        throw Exception("fFlea canNot fly")
    }

    fun crawl() = "Flea: crawl"
}

fun Insect.basic() = walk() + " " + if (this is Flea) crawl() else fly()

