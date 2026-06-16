package ch05.atom65

class SpaceShipControls: Controls {
    override fun up(velocity: Int): String {
        return "up"
    }

    override fun down(velocity: Int): String {
        return "down"
    }

    override fun left(velocity: Int): String {
        return "left"
    }

    override fun right(velocity: Int): String {
        return "right"
    }

    override fun forward(velocity: Int): String {
        return "forward"
    }

    override fun back(velocity: Int): String {
        return "back"
    }

    override fun turboBoost(velocity: Int): String {
        return "turboBoost"
    }
}