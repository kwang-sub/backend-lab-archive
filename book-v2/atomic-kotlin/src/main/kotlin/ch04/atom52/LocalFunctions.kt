package ch04.atom52

import java.lang.StringBuilder

fun main() {
    val logMsg = StringBuilder()
    fun log(message: String) = logMsg.appendLine(message)
    log("Starting computation")
    val x = 42
    log("$x")
    println(logMsg)
}