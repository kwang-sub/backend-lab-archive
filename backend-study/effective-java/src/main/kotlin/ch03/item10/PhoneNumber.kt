package ch03.item10

import kotlin.Comparator

class PhoneNumber private constructor(
    private val areaCode: Short,
    private val prefix: Short,
    private val lineNum: Short,
) : Cloneable, Comparable<PhoneNumber> {
    private var hashCode: Int = 0

    constructor(areaCode: Int, prefix: Int, lineNum: Int) : this(
        areaCode = rangeCheck(areaCode, 999, "지역코드"),
        prefix = rangeCheck(prefix, 999, "지역코드"),
        lineNum = rangeCheck(lineNum, 999, "지역코드"),
    )

    override fun equals(other: Any?): Boolean {
        if (other == this) return true
        if (other !is PhoneNumber) return false
        return other.lineNum == lineNum && other.prefix == prefix && other.areaCode == areaCode
    }

    override fun hashCode(): Int {
        var result = hashCode
        if (result == 0) {
            result = areaCode.hashCode()
            result = 31 * result + prefix.hashCode()
            result = 31 * result + lineNum.hashCode()
            hashCode = result
        }
        return result
    }

    override fun toString(): String {
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum)
    }

    override fun clone(): PhoneNumber {
        try {
            return super.clone() as PhoneNumber
        } catch (e: CloneNotSupportedException) {
            throw AssertionError()
        }
    }

    override fun compareTo(other: PhoneNumber): Int {
        return COMPARATOR.compare(this, other)
    }

    companion object {
        fun rangeCheck(value: Int, max: Int, arg: String): Short {
            if (value !in 0..max) throw IllegalArgumentException("$arg: $value")
            return value.toShort()
        }

        private val COMPARATOR: Comparator<PhoneNumber> = Comparator
            .comparingInt<PhoneNumber> {it.areaCode.toInt()}
            .thenComparingInt {it.prefix.toInt()}
            .thenComparingInt {it.lineNum.toInt()}
    }
}