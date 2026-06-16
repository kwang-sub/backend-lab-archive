package ch03.atom33

fun main() {
    processInputs(listOf("up", "d", "nowhere","r" ,"r"))
}

class Coordinated{
    var x: Int = 0
        set(value) {
            println("x gets $value")
            field = value
        }
    var y: Int = 0
        set(value) {
            println("y gets $value")
            field = value
        }

    override fun toString(): String {
        return "Coordinated(x=$x, y=$y)"
    }
}

fun processInputs(inputs: List<String>) {
    val coordinates = Coordinated()
    for (input in inputs) {
        when (input) {
            "up", "u" -> coordinates.y--
            "down", "d" -> coordinates.y++
            "left", "l" -> coordinates.x--
            "right", "r" -> coordinates.x++
            "nowhere" -> {}
            "exit" -> return
            else -> println("bad input: $input")
        }
    }
}

