package ko.ch01

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val a = br.readLine().toInt()
    val b = br.readLine().toInt()
    val c = br.readLine().toInt()
    var max = a
    if (max < b) max = b
    if (max < c) max = c

    println("Max is $max")
}