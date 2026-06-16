package ch05.atom69

class Airport(private val code: String) {
    open class Plane {
        fun contact(airport: Airport): String {
            return "Contacting ${airport.code}"
        }
    }
    private class PrivatePlane: Plane()
    fun privatePlane(): Plane = PrivatePlane()
}

fun main() {
    val denver = Airport("DEN")
    val plane = Airport.Plane()
    println(plane.contact(denver))

    val privatePlane = denver.privatePlane()
    println(privatePlane.contact(denver))
}