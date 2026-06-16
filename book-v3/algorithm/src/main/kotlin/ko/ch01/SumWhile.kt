package ko.ch01

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
//    println(sumWhile(3))
//    println(sumFor(3))
    sumForPos()
}

fun sumWhile(n: Int): Int {
    var result = 0
    var i = 1
    while (i <= n) {
        result += i
//        println(i)
        i++
    }
    return result
}

fun sumFor(n: Int): Int {
    var result = 0
    for (i in 1 .. n) {
        result += i
    }
    return result
}

fun sumForPos() {
    println("1부터 n까지의 합을 구합니다.")
    val br = BufferedReader(InputStreamReader(System.`in`))
    var n = 0
    do {
        println("n의 값: ")
        n = br.readLine().toInt()
    } while (n <= 0)

//    val sum = IntRange(1, n).sum()
    var sum = 0
    for (i in 1 .. n) {
        sum += i
    }

    println("합계 $sum")
}
