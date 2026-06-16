package ko.ch02

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    maxOfArray()
}

fun maxOfArray() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    println("키의 최댓값을 구합니다.")
    print("사람 수 : ")
    val num = br.readLine().toInt()

    val height = IntArray(num)

    for (i in 0 until num) {
        print("heigth[$i] = ")
        height[i] = br.readLine().toInt()
    }

    println("최댓값은 ${maxOf(height)}")
}

fun maxOf(height: IntArray): Int {
    var max = height[0]

    for (i in 1 until height.size) {
        if (height[i] > max) {max = height[i] }
    }

    return max
}
