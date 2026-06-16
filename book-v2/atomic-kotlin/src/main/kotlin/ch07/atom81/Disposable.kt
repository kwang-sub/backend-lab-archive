package ch07.atom81

interface Disposable {
    val name: String
    fun action(): String
}

class Compost(override val name: String) : Disposable {
    override fun action(): String = "Add to composter"
}

interface Transport : Disposable

class Donation(override val name: String) : Transport {
    override fun action(): String = "Call for pickup"
}

class Recyclable(override val name: String) : Transport {
    override fun action(): String = "Put in bin"
}

class Landfill(override val name: String) : Transport {
    override fun action(): String = "Put in dumpster"
}

val items = listOf(
    Compost("Orange Peel"),
    Compost("Orange Peel"),
)