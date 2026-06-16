package ko.ch02

fun main() {
//    intArray()
//    intArrayInit()
    cloneArray()
}

fun intArray() {
    val a = IntArray(5)

    a[1] = 37
    a[2] = 51
    a[4] = a[1] * 2

    for (i in a.withIndex()) {
        println("a[${i.index}] = ${i.value}")
    }
}

fun intArrayInit() {
    val a = intArrayOf(1, 2, 3, 4, 5)

    for (i in a.withIndex()) {
        println("a[${i.index}] = ${i.value}")
    }
}

fun cloneArray() {
    val a = intArrayOf(1, 2, 3, 4, 5)
    val b = a.clone()

    b[3] = 0

    print("a = ")
    for (i in a) {
        print(" $i")
    }

    print("\nb = ")
    for (i in b) {
        print(" $i")
    }
}