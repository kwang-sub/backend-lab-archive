package ch04

class Person4(
    val firstName: String,
    val lastName: String,
) {
    companion object {

    }
}

fun Person4.Companion.fromJSON(json: String): Person4 {
    return Person4("test", "test")
}