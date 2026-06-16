package ch06

fun main() {

    showProgress(146)

    println(Person5("sam", 35).isOlderThan(Person5("Amy", 42)))
    println(Person5("sam", 35).isOlderThan(Person5("Amy")))

    val i: Int = 1
    val l: Long = 1
    println(i == l.toInt())
}

fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("We're ${percent}% done!")
}