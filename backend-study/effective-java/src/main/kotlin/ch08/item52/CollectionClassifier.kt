package ch08.item52

import java.util.*

class CollectionClassifier {
    companion object {
        fun classify(s: MutableSet<*>): String {
            return "집합"
        }

        fun classify(lst: MutableList<*>): String {
            return "리스트"
        }

        fun classify(c: MutableCollection<*>): String {
            return "그 외"
        }
    }
}

fun main() {
    val collections = listOf(
        HashSet<String>(),
        mutableListOf<String>(),
        hashMapOf<String, String>().values,
    )

    for (c: MutableCollection<String> in collections) {
        println(CollectionClassifier.classify(c))
    }
}