package ch05.atom65

class ExplicitControls : Controls {
    private val controls = SpaceShipControls()
    override fun up(velocity: Int): String {
        return controls.up(velocity)
    }

    override fun down(velocity: Int): String {
        return controls.down(velocity)
    }

    override fun left(velocity: Int): String {
        return controls.left(velocity)
    }

    override fun right(velocity: Int): String {
        return controls.right(velocity)
    }

    override fun forward(velocity: Int): String {
        return controls.forward(velocity)
    }

    override fun back(velocity: Int): String {
        return controls.back(velocity)
    }

    override fun turboBoost(velocity: Int): String {
        return controls.turboBoost(velocity)
    }
}