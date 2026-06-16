package ch02.item03.ch01

class Elvis private constructor() {

    fun leaveTheBuilding() {}

    private fun readResolve(): Any = INSTANCE

    companion object {
        private val INSTANCE: Elvis = Elvis()
        fun getInstance(): Elvis = INSTANCE
    }
}

enum class ElvisEnum {
    INSTANCE;

    fun leaveTheBuilding() {}
}