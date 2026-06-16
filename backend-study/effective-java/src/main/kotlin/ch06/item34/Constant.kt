package ch06.item34

const val APPLE_FUJI: Int = 0
const val APPLE_PIPPIN: Int = 1
const val APPLE_GRANNY_SMITH: Int = 2

const val ORANGE_NAVEL: Int = 0
const val ORANGE_TEMPLE: Int = 1
const val ORANGE_BLOOD: Int = 2

enum class Apple { FUJI, PIPPIN, GRANNY_SMITH }
enum class Orange { NAVEL, TEMPLE, BLOOD }

fun main() {
    val i = (APPLE_FUJI - ORANGE_TEMPLE) / APPLE_PIPPIN
}