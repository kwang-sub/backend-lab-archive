package ko.ch02

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    reverseArray()
}

fun reverseArray() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    print("요솟수: ")
    val num = br.readLine().toInt()

    val x = IntArray(num)
    for (i in 0 until num) {
        print("x[$i]: ")
        x[i] = br.readLine().toInt()
    }

    reverse(x)
}

fun reverse(x: IntArray) {
    for (i in 0 until x.size / 2) {
        swap(x, i, x.size - 1 - i)
    }
}

fun swap(x: IntArray, i: Int, j: Int) {
    println(x.joinToString(", "))
    println("a[$i]와 a[$j]를 교환합니다.")
    val tmp = x[i]
    x[i] = x[j]
    x[j] = tmp
    println(x.joinToString(", "))
}
