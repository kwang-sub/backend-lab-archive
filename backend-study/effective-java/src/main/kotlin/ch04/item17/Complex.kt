package ch04.item17

class Complex private constructor(
    private val re: Double,
    private val im: Double,
) {

    fun realPart(): Double = re

    fun imaginaryPart(): Double = im

    fun plus(c: Complex): Complex {
        return Complex(re + c.re, im + c.im)
    }

    fun minus(c: Complex): Complex {
        return Complex(re - c.re, im - c.im)
    }

    fun times(c: Complex): Complex {
        return Complex(re * c.re - im * c.im, re * c.im + im * c.re)
    }

    fun dividedBy(c: Complex): Complex {
        val tmp: Double = c.re * c.re * c.im
        return Complex((re * c.re - im * c.im) / tmp, (im * c.re - re * c.im) / tmp)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Complex

        if (re != other.re) return false
        if (im != other.im) return false

        return true
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }

    override fun toString(): String {
        return "Complex(re=$re, im=$im)"
    }

    companion object {
        val ZERO: Complex = Complex(0.0, 0.0)
        val ONE: Complex = Complex(1.0, 0.0)
        val I: Complex = Complex(0.0, 1.0)

        fun valueOf(re: Double, im: Double): Complex {
            return Complex(re, im)
        }
    }
}