package ch05.atom67

sealed class Top
class Middle1 : Top()
class Middle2 : Top()
open class Middle3 : Top()
class Bottom3 : Middle3()

fun main() {
    println(Top::class.sealedSubclasses.map { it.simpleName })
}
