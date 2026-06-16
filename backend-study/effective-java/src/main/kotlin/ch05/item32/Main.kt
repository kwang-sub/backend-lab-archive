package ch05.item32

fun dangerous(vararg stringLists: MutableList<String>) {
    val intList = mutableListOf(42)
    val objects: MutableList<Any> = stringLists.toMutableList()
    objects[0] = intList
    val s: String = stringLists[0][0]
    println(s)
    println(objects)
}

fun main() {
    dangerous(mutableListOf("ta"), mutableListOf("zxz"))
}