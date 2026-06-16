package ch05.atom69

fun localClasses() {
    open class Amphibian
    class Frog: Amphibian()
    val amphibian: Amphibian = Frog()
}