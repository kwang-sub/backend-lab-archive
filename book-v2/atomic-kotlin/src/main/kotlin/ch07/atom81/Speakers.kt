package ch07.atom81

class Person {
    fun speak() = "Hi!"
}

class Dog {
    fun bark() = "Ruff!"
}

class Robot {
    fun communicate() = "Beep!"
}

fun talk(speaker: Any) = when (speaker) {
    is Person -> speaker.speak()
    is Dog -> speaker.bark()
    is Robot -> speaker.communicate()
    else -> "Not a talker"
}