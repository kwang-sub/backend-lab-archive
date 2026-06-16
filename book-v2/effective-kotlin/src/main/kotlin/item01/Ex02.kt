package item01

fun main() {
    val a = 10
//    a = 20

    val list = mutableListOf(1, 2, 3)
    list.add(4)
    println(list)
    println()

    println(fullName)
    name = "Change"
    println(fullName)
    println()

    println(fizz)
    println(fizz)
    println(buzz)
    println(buzz)
    println()

    if (fullName != null)
//        println(fullName.length)

    if (fullName2 != null)
        println(fullName2.length)


}

var name: String? = "Marcin"
var surname: String = "Moskala"

val fullName: String?
    get() = name?.let { "$it $surname" }

val fullName2: String? = name?.let { "$it $surname" }

fun calculate(): Int {
    println("Calculating...")
    return 42
}

val fizz = calculate()
val buzz
    get() = calculate()
