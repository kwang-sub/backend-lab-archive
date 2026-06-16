package ch09

class Processor<T : Any> {
    fun processor(value: T) {
        value.hashCode()
    }
}