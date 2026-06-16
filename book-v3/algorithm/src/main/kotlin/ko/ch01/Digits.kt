package ko.ch01

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
//    digits()
    multi99Table()
}


fun digits() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    println("2자리의 정수를 입력하세요.")
    var n = 0
//    while (n < 10 || n > 99){
//        print("n: ")
//        n = br.readLine().toInt()
//    }

    while (!(n >= 10 && n <= 99)){
        print("n: ")
        n = br.readLine().toInt()
    }
    println(n)
}

fun multi99Table() {
    println("-------곱셈표----------")
    for (n in 1..9) {
        for (m in 1..9) {
            print("%3d".format(n * m))
        }
        println()
    }
}