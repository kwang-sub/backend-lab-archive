package ch07.atom79

val zero: Int.() -> Boolean = { this == 0 }

val one: Int.(Int) -> Boolean = { this % it == 0 }

val two: Int.(Int, Int) -> Boolean = { arg1, arg2 -> this % (arg1 + arg2) == 0 }

