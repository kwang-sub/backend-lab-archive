package ch03.atom31

class Color(
    val red: Int = 0,
    val green: Int = 0,
    val blue: Int = 0,
) {
    override fun toString() = "($red, $green, $blue)"
}