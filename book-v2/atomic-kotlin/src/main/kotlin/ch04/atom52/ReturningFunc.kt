package ch04.atom52

fun first(): (Int) -> Int {
    val func = fun(i: Int) = i + 1
    println(func(1))
    return func
}

fun second(): (String) -> String {
    val func2 = {s: String -> "$s!"}
    println(func2("abc"))
    return func2
}

fun third(): () -> String {
    fun greet() = "Hi!"
    return ::greet
}

fun fourth() = fun() = "Hi!"

fun fifth() = { "Hi!" }


fun main() {
    val funRef1 = first()
    val funRef2 = second()
    val funRef3 = third()
    val funRef4 = fourth()
    val funRef5 = fifth()

    println(funRef1(1))
    println(funRef2("abd"))
    println(funRef3())
    println(funRef4())
    println(funRef5())
}

