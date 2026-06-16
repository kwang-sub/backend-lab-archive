package ch07.atom81

val outCatBox: OutBox<Cat> = OutBox(Cat())
val outPetBox: OutBox<Pet> = outCatBox
val outAnyBox: OutBox<Any> = outCatBox

val test: InBox<Pet> = InBox(Cat())

fun main() {
    test.put(Pet())
}