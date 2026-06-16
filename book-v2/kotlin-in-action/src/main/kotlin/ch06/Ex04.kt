package ch06

fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) {
        println("Please fill in the required fields")
    }
}

fun <T:Any> printHashCode(t: T) {
    println(t.hashCode())
}

fun main() {
    verifyUserInput(" ")
    verifyUserInput(null)

    printHashCode("null")
}