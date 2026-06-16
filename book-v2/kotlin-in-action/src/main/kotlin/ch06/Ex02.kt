package ch06

fun main() {
    val person = Person("kwang", null)
    println(person.countryName())

    println(strLenSafe(null))
    println(strLenSafe("null"))
    
    val person2 = Person2("kwang", Company("com", Address("300", 123, "독산", "금천구")))
    person2.printShippingLabel()
}

fun foo(s: String?) {
    val t: String = s ?: ""
}

fun strLenSafe(s: String?): Int = s?.length ?: 0