package ch05.atom67

sealed class Transport

data class Train(
    val line: String
): Transport()

data class Bus(
    val number: String,
    val capacity: Int
): Transport()

fun travel(transport: Transport): String {
    return when (transport) {
        is Train -> "Train ${transport.line}"
        is Bus -> "Bus ${transport.number} ${transport.capacity}"
    }
}

