package modernjavainaction.study.ch18

fun main() {
    listOf(1, 4, 9).subsets()
        .map { println(it) }
}

fun List<Int>.subsets(): List<List<Int>> {
    if (this.isEmpty()) return listOf(emptyList())

    val first = this[0]
    val rest = this.subList(1, this.size)
    val subAns = rest.subsets()
    val subAns2 = first.insertAll(subAns)

    return concat(subAns, subAns2)
}


fun Int.insertAll(lists: List<List<Int>?>): List<List<Int>> {
    val result: MutableList<List<Int>> = ArrayList()
    for (l in lists) {
        val copyList: MutableList<Int> = ArrayList()
        copyList.add(this)
        copyList.addAll(l!!)
        result.add(copyList)
    }
    return result
}

fun <T> concat(a: List<List<T>>?, b: List<List<T>>?): List<List<T>> {
    val r: MutableList<List<T>> = java.util.ArrayList(a)
    r.addAll(b!!)
    return r
}