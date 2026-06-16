package ch07.atom84

import kotlin.reflect.KProperty

class Readable(val i: Int) {
    val value: String by BasicRead()
}

class BasicRead {
    operator fun getValue(r: Readable, property: KProperty<*>) = "getValue: ${r.i}"
}

fun main() {
    val x = Readable(11)
    val y = Readable(17)
    println(x.value)
    println(y.value)
}