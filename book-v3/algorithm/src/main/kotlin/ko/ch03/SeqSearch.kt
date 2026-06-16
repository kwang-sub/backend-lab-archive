package ko.ch03

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    println("요솟수: ")
    val num = br.readLine().toInt()
    val x = IntArray(num + 1)
    for (i in 0 until num) {
        print("x[$i]: ")
        x[i] = br.readLine().toInt()
    }
    println("검색할 값: ")
    val key = br.readLine().toInt()
    val findKey = seqSearchSenPrint(x, num, key)
    if (findKey == -1) println("그 값의 요소가 없습니다.")
    else println("${key}는 x[$findKey]에 있습니다.")
}

fun seqSearch(a: IntArray, n: Int, key: Int): Int {
    for (i in 0 until n) {
        if (a[i] == key) return i
    }

    return -1
}

fun seqSearchSen(a: IntArray, n: Int, key: Int): Int {
    var i = 0
    a[n] = key
    while (true) {
        if (a[i] == key) break
        i++
    }
    return if (i == n) -1 else i
}

fun seqSearchSenFor(a: IntArray, n: Int, key: Int): Int {
    a[n] = key
    var i = 0
    while (a[i] != key) {
        i++
    }
    return if (i == n) -1 else i
}

fun seqSearchSenPrint(a: IntArray, n: Int, key: Int): Int {
    a[n] = key
    print(String.format("%3s", "|"))
    for (j in 0 until n) {
        print(String.format("%3d", j))
    }
    println("\n--+--------------------------")

    var i = 0
    while (true) {
        print(String.format("%3s", "|"))
        val p = (i + 1) * 3
        println("*".padStart(p))
        print(String.format("%1d |", i))
        for (j in 0 until n) {
            print(String.format("%3d", a[j]))
        }
        println()
        if (a[i] == key) break
        i++
    }
    return if (i == n) -1 else i
}