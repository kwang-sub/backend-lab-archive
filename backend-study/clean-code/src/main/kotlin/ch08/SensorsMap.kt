package ch08

class SensorsMap {
    private val sensors: MutableMap<Long, Sensors> = mutableMapOf()

    fun add(sensors: Sensors) {
        this.sensors[sensors.id] = sensors
    }

    fun getById(id: Long): Sensors =
        sensors[id] ?: throw IllegalArgumentException("not found sensors")
}
