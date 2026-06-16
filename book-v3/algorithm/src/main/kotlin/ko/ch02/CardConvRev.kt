package ko.ch02

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))

    var no: Int
    var cd: Int
    var dno: Int
    val cno = CharArray(32)

    println("10진수를 기수 변환합니다.")
    do {
        print("변환하는 음이 아닌 정수: ")
        no = br.readLine().toInt()
    } while (no < 0)

    do {
        print("어떤 진수로 변환할까요? (2~36) : ")
        cd = br.readLine().toInt()
    } while (cd < 2 || cd > 36)

    dno = cardConv(no, cd, cno)

    print("${cd}진수로는 ")
//    for (i in dno - 1 downTo 0) {
//        print(cno[i])
//    }
    for (i in 0..dno) {
        print(cno[i])
    }
    println("입니다")
}

fun cardConvRev(x: Int, r: Int, d: CharArray): Int {
    var x = x
    var digits = 0
    val dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    do {
        d[digits++] = dchar[x % r]

        x /= r
    } while (x != 0)

    return digits
}

fun cardConv(x: Int, r: Int, d: CharArray): Int {
    var x = x
    var digits = 0
    val dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    do {
        d[digits++] = dchar[x % r]

        x /= r
    } while (x != 0)

    for (i in 0.. digits / 2) {
        val temp = d[i]
        d[i] = d[digits - i - 1]
        d[digits - i - 1] = temp
    }

    return digits - 1
}