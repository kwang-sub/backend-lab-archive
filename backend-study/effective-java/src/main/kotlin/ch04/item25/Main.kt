package ch04.item25

import java.util.Arrays

class Main {
    companion object {
        fun main(args: Array<String>) {
            println("${Utensil.NAME} ${Dessert.NAME}")
        }
    }
}

fun <T> Array<T>.toArray(a: Array<T?>): Array<out T?> {
    if (a.size < this.size) {
        return Arrays.copyOf(this, this.size, a::class.java)
    }
    System.arraycopy(this, 0, a, 0, this.size)
    if (a.size > size) {
        a[size] = null;
    }
    return a
}


fun main() {
    val list = mutableListOf<Int>(1)

    val list2 = mutableListOf(list)

    println(list2)
}