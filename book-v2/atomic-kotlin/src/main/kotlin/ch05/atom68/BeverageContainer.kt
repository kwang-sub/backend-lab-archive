package ch05.atom68

interface BeverageContainer {
    fun open(): String
    fun pour(): String
}

class Can : BeverageContainer {
    override fun open(): String {
        return "Pop Top"
    }

    override fun pour(): String {
        return "Can: Pour"
    }
}

open class Bottle : BeverageContainer {
    override fun open(): String {
        TODO("Not yet implemented")
    }

    override fun pour(): String {
        TODO("Not yet implemented")
    }
}

