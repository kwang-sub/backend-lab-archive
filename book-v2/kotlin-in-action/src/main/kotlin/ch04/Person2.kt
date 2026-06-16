package ch04

class Person2(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Person2 = Person2(jsonText)
    }

}