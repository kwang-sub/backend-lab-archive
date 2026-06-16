package modernjavainaction.study.ch13

interface Rotatable {

    fun setRotationAngle(angleInDegrees: Int)

    fun getRotationAngle(): Int

    fun rotateBy(angleInDegrees: Int) {
        setRotationAngle((getRotationAngle() + angleInDegrees) % 360)
    }
}