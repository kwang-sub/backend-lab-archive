package ch05.atom70

open class Paint(val color: String) {
    open fun apply() = "Applying $color"
}

object Acrylic : Paint("Blue") {
    override fun apply() = "Acrylic, ${super.apply()}"
}

interface PaintPreparation {
    fun prepare(): String
}

object Prepare : PaintPreparation {
    override fun prepare(): String {
        return "Scrape"
    }
}

fun main() {
    println(Prepare.prepare())
    println(Paint("Red").apply())
    println(Acrylic.apply())
}