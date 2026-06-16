package ch08

class Sensors {
    private var _id: Long? = null
    val id: Long
        get() = _id ?: throw IllegalArgumentException("not found sensors")

    companion object {
        fun create(id: Long): Sensors {
            return Sensors()
                .apply { this._id = id }
        }
    }
}
