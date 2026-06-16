package book.chap03.question

fun main() {
//    seqSearchPrint(intArrayOf(1, 2, 3, 4, 5), 2)
    binSearchPrint(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 10)
}

fun seqSearchPrint(arr: IntArray, key: Int) {
    var result = -1

    print("    |")
    val intRange = IntRange(0, arr.size - 1)
    intRange.forEach {
        print(String.format("%3d", it))
    }
    println("\n----+------------------------")

    for (it in intRange) {
        print(String.format("    |%${3 * (it + 1)}s", "*"))
        print(String.format("\n%3d |", it))
        for (a in arr) {
            print(String.format("%3d", a))
        }
        println("\n    |")
        if (arr[it] == key) {
            result = it
            break
        }
    }
    if (result == -1) println("존재하지 않습니다.")
    else println("${key}은 x[${result}]에 있습니다.")
}

fun searchIdx(a: List<Int>, n: Int, key: Int, idx: MutableList<Int>): Int {
    a.forEachIndexed { index, i -> if (i == key) idx.add(index) }
    return idx.size
}

fun binSearchPrint(list: List<Int>, key: Int) {
    print("   |")
    for (i in list) {
        print(String.format("%3d", i))
    }
    var pl = 0
    var pr = list.size - 1
    println("\n---+--------------------")
    for (i in list) {
        val pc = (pl + pr) / 2
        if (list[pc] == key) {
            println("${key}는 x[$pc]에 있습니다.")
            break
        }
        print(String.format("   |%${(pl + 3)}s", "<-"))
        print(String.format("%${(pc + 3)}s", "*"))
        print(String.format("%${(pr + 3)}s", "->"))
        print(String.format("\n%3d|", i))
        for (i in list) {
            print(String.format("%3d", i))
        }
        println()
        if (list[pc] <= key) {
            pl = pc + 1
        } else {
            pr = pc - 1
        }
    }
}

fun binSearchX(a: List<Int>, key: Int): Int {
    var pl = 0;
    var pr = a.size - 1

    var result = -1
    for (i in a) {
        val pc = (pl + pr) / 2
        if (a[pc] == key) {
            result = checkPc(a, pc)
        } else if (a[pc] <= key) {
            pl = pc + 1
        } else {
            pr = pc - 1
        }
    }

    return result
}

fun checkPc(a: List<Int>, pc: Int): Int {
    return if (pc == 0) {
        pc
    } else if (a[pc - 1] == a[pc]) {
        checkPc(a, pc - 1)
    } else pc
}
