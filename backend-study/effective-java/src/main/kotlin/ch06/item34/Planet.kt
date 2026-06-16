package ch06.item34

enum class Planet(
    val mass: Double,
    val radius: Double,
) {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    val surfaceGravity: Double
        get() = G * mass / (radius * radius)

    fun surfaceWeight(mass: Double): Double {
        return mass * surfaceGravity
    }

    companion object {
        const val G: Double = 6.67300E-11
    }
}