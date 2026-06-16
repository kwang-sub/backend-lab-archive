package ko.ch03

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    print("요솟수: ")
    val n = br.readLine().toInt()
    val intArray = IntArray(n)

    for (i in 0 until n) {
        print("array[$i]: ")
        intArray[i] = br.readLine().toInt()
    }
    print("key: ")
    val key = br.readLine().toInt()

    println(binSearch(intArray, n, key))
}

fun binSearch(a: IntArray, n: Int, key: Int): Int {
    var pl = 0
    var pr = n - 1
    do {
        val pc = (pl + pr) / 2
        if (a[pc] == key) return pc
        else if (a[pc] > key) {
            pr = pc - 1
        } else {
            pl = pc + 1
        }

    } while (pl <= pr)

    return -1
}