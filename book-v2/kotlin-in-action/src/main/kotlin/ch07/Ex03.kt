package ch07

fun main() {
    val p = Point(1, 2)
    val (z) = p
    println(z)

    val (name, ext) = splitFileName("example.kt")
    println(name)
    println(ext)

    printEntries(mapOf("1" to "2"))

    val foo = Foo()
    println(foo.p)
    foo.p = "tt"
    println(foo.p)
}

fun printEntries(map: Map<String, String>) {
    for ((key, value) in map) {
        println("$key -> $value")
    }
}