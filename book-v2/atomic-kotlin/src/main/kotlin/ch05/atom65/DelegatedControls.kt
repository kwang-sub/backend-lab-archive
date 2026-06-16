package ch05.atom65

class DelegatedControls(
    private val controls: SpaceShipControls = SpaceShipControls()
): Controls by controls {

}