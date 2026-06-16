package ko.ch02

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    arrayEqual()
}

fun arrayEqual() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    print("배열 a의 요솟수: ")
    val na = br.readLine().toInt()

    val a = IntArray(na)

    for (i in 0 until na) {
        print("a[$i] = ")
        a[i] = br.readLine().toInt()
    }

    print("배열 b의 요솟수: ")
    val nb = br.readLine().toInt()

    val b = IntArray(nb)

    for (i in 0 until nb) {
        print("b[$i] = ")
        b[i] = br.readLine().toInt()
    }

    println("배열 a와 b는 ${if (equal(a, b)) "같습니다." else "같지 않습니다."}")


}

fun equal(a: IntArray, b: IntArray): Boolean {
    if (a.size != b.size) return false
    for (i in a.withIndex()) if (b[i.index] != i.value) return false
    return true
}
