package ch03.item11

import ch03.item10.PhoneNumber


fun main() {
    val m: HashMap<PhoneNumber, String> = hashMapOf()
    m[PhoneNumber(1,2,3)] = "제니"
    val get = m[PhoneNumber(1, 2, 3)]
    println(get)
}