package ch06.item35

enum class Ensemble(
    private val numberOfMusicians: Int,
) {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4),
    QUINTET(5), SEXTET(6), SEPTET(7), OCTET(8),
    NONET(9), DECTET(10);

    fun numberOfMusicians(): Int {
        return ordinal + 1
    }
}