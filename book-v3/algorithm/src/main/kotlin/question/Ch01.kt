package question

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.IntStream

fun main() {
//    q7(2)
//    println(q8(5))
//    println(q9(6, 4))
    q16()
}

fun q7(n: Int) {
    var sum = 0
    val sb = StringBuilder()
    IntStream.rangeClosed(1, n).forEach {
        if (it != n) {
            sb.append("$it + ")
        } else {
            sb.append("$it = ")
        }
        sum += it
    }

    sb.append(sum)

    println(sb.toString())
}

fun q8(n: Int): Int {
    var sum = (1 + n) * (n / 2)
    if (n % 2 != 0) sum += n / 2 + 1

    return sum
}

fun q9(a: Int, b: Int): Int {
    var sum = 0
    if (a > b) {
        for (i in b..a) {
            sum += i
        }
    } else {
        for (i in a..b) {
            sum += i
        }
    }
    return sum
}

fun q10() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    print("a의 값: ")
    val a = br.readLine().toInt()
    var b = Int.MIN_VALUE
    do {
        print("b의 값: ")
        b = br.readLine().toInt()
        if (a >= b) println("a보다 큰 값을 입력하세요!")
    } while (a >= b)
    println("b - a는 ${b - a}입니다.")
}

fun q11() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var n = 0
    while (n <= 0) {
        print("n: ")
        n = br.readLine().toInt()
    }

    var count = 0
    while (n > 0) {
        n /= 10
        count++
    }

    println("그 수는 ${count}자리입니다.")
}

fun q12() {
    print("   |")
    for (i in 1..9) {
        print(String.format("%3d", i))
    }
    println("\n---+--------------------------")
    for (i in 1..9) {
        for (j in 1..9) {
            if (j == 1)
                print(String.format("%2d |", i))

            print(String.format("%3d", i * j))
        }
        println()
    }
}

fun q13() {
    print("   |")
    for (i in 1..9) {
        print(String.format("%3d", i))
    }
    println("\n----+--------------------------")
    for (i in 1..9) {
        for (j in 1..9) {
            if (j == 1) {
                print(String.format("%2d |", i))
            }
            print(String.format("%3d", i + j))
        }
        println()
    }
}

fun q14() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var n = 0
    while (n <= 0) {
        print("n: ")
        n = br.readLine().toInt()
    }

    for (i in 1..n) {
        for (j in 1..n) {
            print("*")
        }
        println()
    }
}

fun q16() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var n = 0
    while (n <= 0) {
        print("n: ")
        n = br.readLine().toInt()
    }

    for (i in 1..n) {
        for (j in 1..n - i) print(" ")
        for (j in 1..(i - 1) * 2 + 1) print(i % 10)
        println()
    }
}