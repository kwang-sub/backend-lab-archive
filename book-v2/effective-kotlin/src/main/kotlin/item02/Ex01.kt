package item02


fun main() {
    val a = 1
    fun fizz() {
        val b = 2
        println(a + b)
    }

    val buzz = {
        val c = 3
        println(a + c)
    }
    fizz()
    buzz()
}

fun updateWeather2(degrees: Int) {
    val description: String
    val color: Int
    if (degrees < 5) {
        description = "cold"
        color = Color.BLUE
    } else if (degrees < 23) {
        description = "mild"
        color = Color.YELLOW
    }
}


fun updateWeather(degrees: Int) {
    val (description, color) = when {
        (degrees < 5) -> "cold" to Color.BLUE
        (degrees < 23) -> "mild" to Color.YELLOW
        else -> "hot" to Color.RED
    }
}

class Color {
    companion object {
        const val BLUE = 1
        const val YELLOW = 2
        const val RED = 3
    }
}
