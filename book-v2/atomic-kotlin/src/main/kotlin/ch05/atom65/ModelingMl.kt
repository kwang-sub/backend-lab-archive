package ch05.atom65

interface Rectangle {
    fun paint(): String
}

class ButtonImage(
    val width: Int,
    val height: Int
) : Rectangle {
    override fun paint(): String {
        return "painting ButtonImage($width, $height)"
    }
}

interface MoseManager {
    fun clicked(): Boolean
    fun hovering(): Boolean
}

class UserInput : MoseManager {
    override fun clicked(): Boolean {
        return true
    }

    override fun hovering(): Boolean {
        return true
    }
}

class Button(
    val width: Int,
    val height: Int,
    private val image: Rectangle = ButtonImage(width, height),
    private val input: MoseManager = UserInput()
): Rectangle by image, MoseManager by input