package ch02.atom18

class House {
    var sofa: String = ""
}

fun main() {
    val house = House()

    house.sofa = "Simple sleeper sofa: $89.00"
    println(house.sofa)

    house.sofa = "New leather sofa: $3.099.00"
    println(house.sofa)
}