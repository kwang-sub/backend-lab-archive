package ko.ch02

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var retry = 0

    println("그 해 경과 일수를 구합니다.")

    do {
        print("년: ")
        val year = br.readLine().toInt()

        print("월: ")
        val month = br.readLine().toInt()

        print("일: ")
        val day = br.readLine().toInt()

        println("그 해 ${leftDayOfYear(year, month, day)}일째입니다.")
        println("한번 더 할까요? (1.예 / 0.아니요): ")
        retry = br.readLine().toInt()
    } while (retry == 1)
}

fun dayOfYear(y: Int, m: Int, d: Int): Int {
    val mdays = Array(2) {
        intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        intArrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    }
    var days = d
    for (i in 1 until m) {
        days += mdays[isLeap(y)][i - 1]
    }
    return days
}

fun dayOfYearV2(y: Int, m: Int, d: Int): Int {
    val mdays = Array(2) {
        intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        intArrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    }
    var month = m
    var days = d
    while (--month > 0) {
        days += mdays[isLeap(y)][m]
    }
    return days
}

fun leftDayOfYear(y: Int, m: Int, d: Int): Int {
    val mdays = Array(2) {
        intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        intArrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    }
    var days = mdays[isLeap(y)][m - 1] - d
    for (i in m + 1..12) {
        days += mdays[isLeap(y)][i]
    }
    return days
}

fun isLeap(year: Int): Int {
    return if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 1 else 0
}