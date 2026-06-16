package ch05

fun main() {
    val errors = listOf<String>("403 Forbidden", "404 Not Found", "500 Internal Server Error")
    printMessagesWithPrefix(errors, "Error: ")
    printProblemCounts(errors)
}

fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it")
    }
}

fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("client: $clientErrors  server: $serverErrors")
}