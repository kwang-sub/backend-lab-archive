package ch05.atom67

abstract class Abstract(val ab: String)

open class Concrete(): Abstract("")

sealed class Sealed(val av: String)

open class SealedSubClass(): Sealed("")


