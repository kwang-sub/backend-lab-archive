package ch04

fun main() {
    val client = Client("test", 12)
    val copy = client.copy(name = "kwang")
    println(client)
    println(copy)

    A.bar()

    println(Person2.Loader.fromJSON("tt").name)
}