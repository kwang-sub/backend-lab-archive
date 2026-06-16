package ko.ch01

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    triangleLB()
    triangleLU()
    triangleRU()
    triangleRB()
}

fun triangleLB() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var n = 0
    while (n <= 0) {
        print("n: ")
        n = br.readLine().toInt()
    }

    for (i in 1..n) {
        for (j in 1..i) {
            print("*")
        }
        println()
    }
}

fun triangleLU() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var n = 0
    while (n <= 0) {
        print("n: ")
        n = br.readLine().toInt()
    }

    for (i in n downTo 1) {
        for (j in 1..i) {
            print("*")
        }
        println()
    }
}


fun triangleRU() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var n = 0
    while (n <= 0) {
        print("n: ")
        n = br.readLine().toInt()
    }

    for (i in 1..n) {
        for (j in 1..n) {
            if (j < i) print(" ")
            else print("*")
        }
        println()
    }
}

fun triangleRB() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var n = 0
    while (n <= 0) {
        print("n: ")
        n = br.readLine().toInt()
    }

    for (i in n downTo 1) {
        for (j in 1..n) {
            if (j >= i) print("*")
            else print(" ")
        }
        println()
    }
}