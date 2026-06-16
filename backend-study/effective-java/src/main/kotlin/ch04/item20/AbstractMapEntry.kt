package ch04.item20

abstract class AbstractMapEntry<K, V>: Map.Entry<K, V> {
    override var value: V
        get() = value
        set(value: V) = throw UnsupportedOperationException()

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Map.Entry<*, *>) return false
        return key == other.key && value == other.value
    }

    override fun hashCode(): Int {
        return key.hashCode() xor value.hashCode()
    }

    override fun toString(): String {
        return "$key = $value"
    }
}