package ch07.atom79

fun exec(
    arg1: Int, arg2: Int,
    f: Int.(Int) -> Boolean
) = arg1.f(arg2)

fun main() {
    val exec = exec(10, 2, fun Int.(d: Int): Boolean {
        return this % d == 0
    })

    println(exec)
}