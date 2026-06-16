package question

fun main() {
//    val a = intArrayOf(1, 2, 3, 1, 2)
//    println(q3(a, a.size, 2, IntArray(a.size)))
//    q4(2)
    println(intArrayOf(1, 2, 3, 4).binarySearch(3))
}

fun q3(a: IntArray, n: Int, key: Int, idx: IntArray): Int {
    var count = 0
    for (i in a.withIndex()) {
        if (key == i.value) idx[count++] = i.index
    }

    println(idx.toList())
    return count
}

fun q4(key: Int): Int {
    val intArray = intArrayOf(0, 3, 5, 8, 9)
    var pl = 0
    var pr = intArray.size - 1
    print(String.format("%3s", "|"))
    for (i in intArray.indices) {
        print(String.format("%3d", i))
    }
    println("\n--+----------------------")
    do {
        val pc = (pl + pr) / 2
        print(String.format("%3s ", "|"))
        print("<-".padEnd(pl * 2))
        print("+".padStart(pc * 3))
        print("->\n".padStart(pr * 2))
        print(String.format("%2d|", pc))
        for (i in intArray) {
            print(String.format("%3d", i))
        }
        println()
        if (key == intArray[pc]) return pc
        else if (intArray[pc] > key) {
            pr = pc - 1
        } else {
            pl = pc + 1
        }
    } while (pl <= pr)

    return -1
}