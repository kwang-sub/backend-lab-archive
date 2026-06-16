package ch06

fun main() {
    strLen("kwang")
    printAllCaps("abc")
    printAllCaps(null)

    val ceo = Employee("kwang", null)
    val developer = Employee("sub", ceo)

    println(ceo.managerName())
    println(developer.managerName())
}

fun strLen(s: String) = s.length

fun printAllCaps(s: String?) {
    val allCaps: String? = s?.uppercase()
    println(allCaps)
}