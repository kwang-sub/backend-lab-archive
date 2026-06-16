package item05

import item01.name

fun main() {
//    factorial(-1)
    speak("kwang")
    val person = Person("kwnag")
    personTest(person)

}

fun personTest(person: Person) {
    requireNotNull(person.name)
    var name = person.name
}
fun factorial(n: Int): Long {
    require(n >= 0) { "커스텀 메시지" }
    return if (n <= 1) 1 else factorial(n - 1) * n
}

fun speak(text: String) {
//    check(false) { "커스텀 메시지" }
//    checkNotNull("dd")
    assert(1 == 1)
}

class Person(
    var name: String?
)

