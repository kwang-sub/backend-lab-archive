package ch07.atom80

data class Plumbus(var id: Int)

fun display(map: Map<String, Plumbus>) {
    println("displaying $map")
    val pb1: Plumbus = map["main"]?.let {
        it.id += 10
        it
    } ?: return
    println(pb1)

    val pb2: Plumbus? = map["main"]?.run {
        id += 9
        this
    }
    println(pb2)

    val pb3: Plumbus? = map["main"]?.apply {
        id += 8
    }
    println(pb3)


    val pb4: Plumbus? = map["main"]?.also {
        it.id += 7
    }
    println(pb4)
}

fun main() {
    display(mapOf("main" to Plumbus(1)))
    println("=".repeat(10))
    display(mapOf("none" to Plumbus(2)))
}