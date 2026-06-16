package ch06

fun main() {
    val source: Collection<Int> = arrayListOf(1, 2, 3, 4)
    val target: MutableCollection<Int> = arrayListOf(0)
    copyElements(source, target)
    println(target)

    val list = listOf("a", "b", "c")
    printInUppercase(list)
}

fun <T> copyElements(
    source: Collection<T>,
    target: MutableCollection<T>,
) {
    for (item in source) {
        target.add(item)
    }
}

fun printInUppercase(list: List<String>) {
    println(CollectionUtils.uppercaseAll(list))
    println(list.first())
}